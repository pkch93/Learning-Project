package edu.pkch.localstack.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.localstack.LocalStackContainer;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class LocalstackConfig {
    private static Logger logger = LoggerFactory.getLogger(LocalstackConfig.class);
    private LocalStackContainer.Service[] services = {LocalStackContainer.Service.S3, LocalStackContainer.Service.SQS};

    private LocalStackContainer localStackContainer;

    @PostConstruct
    public void init() {
        localStackContainer = new LocalStackContainer();
        try {
            localStackContainer.withServices(services);
            localStackContainer.setPortBindings(
                    Arrays.stream(services)
                            .map(LocalStackContainer.Service::getPort)
                            .map(port -> port + ":" + port)
                            .collect(Collectors.toList())
            );
            localStackContainer.start();
        } catch (Exception ignored) {
            logger.error("localstack load fail. cause by {}", ignored.getMessage(), ignored);
        }
    }

    @PreDestroy
    public void destroy() {
        localStackContainer.close(); // stop과 동일
    }

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(localStackContainer.getAccessKey(), localStackContainer.getSecretKey());

        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(localStackContainer.getEndpointOverride(LocalStackContainer.Service.S3))
                .region(Region.AP_NORTHEAST_2)
                .build();
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(localStackContainer.getAccessKey(), localStackContainer.getSecretKey());

        return SqsAsyncClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(localStackContainer.getEndpointOverride(LocalStackContainer.Service.SQS))
                .build();
    }
}
