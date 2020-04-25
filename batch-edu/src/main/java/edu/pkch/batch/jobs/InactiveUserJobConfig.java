package edu.pkch.batch.jobs;

import edu.pkch.batch.domain.user.User;
import edu.pkch.batch.domain.user.UserRepository;
import edu.pkch.batch.jobs.deciders.InactiveJobExecutionDecider;
import edu.pkch.batch.jobs.readers.QuerydslPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static edu.pkch.batch.domain.user.QUser.user;

@Configuration
public class InactiveUserJobConfig {
    private static final String JOB_NAME = "inactiveUserJob";
    private static final String STEP_NAME = "inactiveUserStep";
    private static final int PAGE_SIZE = 1000;
    private static final LocalDateTime INACTIVE_BASE_DATE = LocalDateTime.now().minusYears(1);

    private final EntityManagerFactory entityManagerFactory;

    private final UserRepository userRepository;

    public InactiveUserJobConfig(EntityManagerFactory entityManagerFactory,
                                 UserRepository userRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.userRepository = userRepository;
    }

    @Bean
    public Job inactiveUserJob(JobBuilderFactory jobBuilderFactory, Flow multipleFlow) {
        return jobBuilderFactory.get(JOB_NAME)
                .preventRestart()
                .start(multipleFlow)
                .end()
                .build();
    }

    public Flow inactiveJobFlow(Step inactiveJobStep) {
        return new FlowBuilder<Flow>("inactiveJobFlow")
                .start(new InactiveJobExecutionDecider())
                .on(FlowExecutionStatus.FAILED.getName()).end()
                .on(FlowExecutionStatus.COMPLETED.getName()).to(inactiveJobStep)
                .end();
    }

    @Bean
    public Flow multipleFlow(Step inactiveJobStep) {
        Flow[] flows = IntStream.range(0, 5)
                .mapToObj(index -> new FlowBuilder<Flow>("flow" + index)
                        .from(inactiveJobFlow(inactiveJobStep))
                        .end()
                )
                .toArray(Flow[]::new);

        return new FlowBuilder<Flow>("multipleFlowBuilder")
                .split(new SimpleAsyncTaskExecutor())
                .add(flows)
                .build();
    }

    @Bean
    public Step inactiveJobStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get(STEP_NAME)
                .<User, User>chunk(10)
                .reader(inactiveUserReader())
                .processor(inactiveUserProcessor())
                .writer(inactiveUserWriter())
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    @Bean
    @StepScope
    public QuerydslPagingItemReader<User> inactiveUserReader() {
        return new QuerydslPagingItemReader<>(entityManagerFactory, PAGE_SIZE,
                queryFactory -> queryFactory.selectFrom(user)
                .where(user.createdAt.before(INACTIVE_BASE_DATE))
        );
    }

    public ItemProcessor<User, User> inactiveUserProcessor() {
        return user -> {
            user.inactive();
            return user;
        };
    }

    public ItemWriter<User> inactiveUserWriter() {
        return userRepository::saveAll;
    }
}
