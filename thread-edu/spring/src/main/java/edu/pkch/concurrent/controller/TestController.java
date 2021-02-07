package edu.pkch.concurrent.controller;

import edu.pkch.concurrent.config.AsyncConfig;
import edu.pkch.concurrent.domain.Article;
import edu.pkch.concurrent.domain.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class TestController {
    private final ArticleRepository articleRepository;
    private final Executor executor;

    public TestController(ArticleRepository articleRepository,
                          @Qualifier(AsyncConfig.THREAD_POOL_EXECUTOR) Executor executor) {
        this.articleRepository = articleRepository;
        this.executor = executor;
    }

    @GetMapping("test")
    public void test() {
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

        log.info("total execute time - {}ms", stopWatch.getTotalTimeMillis());
    }

    @GetMapping("/articles/{id}")
    public Article article(@PathVariable Long id) {
        log.info("read article: {}", id);
        return articleRepository.findById(id)
                .orElse(null);
    }
}
