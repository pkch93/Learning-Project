package edu.pkch.batch.jobs;

import edu.pkch.batch.domain.user.User;
import edu.pkch.batch.domain.user.UserRepository;
import edu.pkch.batch.domain.user.enums.UserStatus;
import edu.pkch.batch.jobs.deciders.InactiveJobExecutionDecider;
import edu.pkch.batch.jobs.readers.QueueItemReader;
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

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class InactiveUserJobConfig {
    private static final String JOB_NAME = "inactiveUserJob";
    private static final String STEP_NAME = "inactiveUserStep";

    private final UserRepository userRepository;

    public InactiveUserJobConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public Job inactiveUserJob(JobBuilderFactory jobBuilderFactory, Flow inactiveJobFlow) {
        return jobBuilderFactory.get(JOB_NAME)
                .preventRestart()
                .start(inactiveJobFlow)
                .end()
                .build();
    }

    @Bean
    public Flow inactiveJobFlow(Step inactiveJobStep) {
        return new FlowBuilder<Flow>("inactiveJobFlow")
                .start(new InactiveJobExecutionDecider())
                .on(FlowExecutionStatus.FAILED.getName()).end()
                .on(FlowExecutionStatus.COMPLETED.getName()).to(inactiveJobStep)
                .end();
    }

    @Bean
    public Step inactiveJobStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get(STEP_NAME)
                .<User, User>chunk(10)
                .reader(inactiveUserReader())
                .processor(inactiveUserProcessor())
                .writer(inactiveUserWriter())
                .build();
    }

    @Bean
    @StepScope
    public QueueItemReader<User> inactiveUserReader() {
        LocalDateTime baseDate = LocalDateTime.now().minusYears(1);
        List<User> oldUsers = userRepository.findByCreatedAtBeforeAndUserStatusEquals(baseDate, UserStatus.ACTIVE);
        return new QueueItemReader<>(oldUsers);
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
