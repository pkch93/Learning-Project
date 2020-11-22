package edu.pkch.concurrent;

import edu.pkch.concurrent.config.AsyncConfig;
import edu.pkch.concurrent.domain.Article;
import edu.pkch.concurrent.domain.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StopWatch;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ThreadPoolTaskExecutorEduTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    @Qualifier(AsyncConfig.THREAD_POOL_EXECUTOR)
    private ThreadPoolTaskExecutor executor;

    @BeforeEach
    void setUp() {
        webTestClient = webTestClient.mutate()
                .responseTimeout(Duration.ofMinutes(1))
                .build();
    }

    @Test
    void name() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        IntStream.rangeClosed(1, 100000)
                .forEach(index -> {
                    Article article = Article.builder()
                            .title("title" + index)
                            .content("content" + index)
                            .build();

                    CompletableFuture.runAsync(() -> articleRepository.save(article), executor);
                });
        stopWatch.stop();

        System.out.println("total execute time - " + stopWatch.getTotalTimeMillis() + "ms");
    }

    @Test
    void name2() {
        Thread
        webTestClient.get()
                .uri("http://localhost:8080/test")
                .exchange();
    }

    @Test
    void name3() {
        IntStream.rangeClosed(1, 1000).forEach(
                id -> webTestClient.get()
                        .uri("http://localhost:8080/articles/" + id)
                        .exchange()
        );
    }
}
