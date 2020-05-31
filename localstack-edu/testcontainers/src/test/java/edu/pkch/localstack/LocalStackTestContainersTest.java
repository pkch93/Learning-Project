package edu.pkch.localstack;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Testcontainers
public class LocalStackTestContainersTest {

    @Container
    private LocalStackContainer localStackContainer = new LocalStackContainer()
            .withServices(LocalStackContainer.Service.S3);

    @Test
    void awsSdkv2() {
        AwsCredentials credentials = AwsBasicCredentials.create(
                localStackContainer.getSecretKey(),
                localStackContainer.getAccessKey()
        );

        S3Client s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .endpointOverride(localStackContainer.getEndpointOverride(LocalStackContainer.Service.S3))
                .region(Region.AP_NORTHEAST_2)
                .build();

        String bucketName = "test-s3";
        s3Client.createBucket(CreateBucketRequest.builder()
                .bucket(bucketName)
                .build());
        log.info("버킷을 생성했습니다. bucketName={}", bucketName);

        String content = "Hello World";
        String key = "s3-key";
        s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build(),
                RequestBody.fromString(content)
        );
        log.info("파일을 업로드하였습니다. bucketName={}, key={}, content={}", bucketName, key, content);

        String actual = s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build(), ResponseTransformer.toBytes()).asUtf8String();
        log.info("파일을 가져왔습니다. bucketName={}, key={}, results={}", bucketName, key, actual);

        assertThat(actual).isEqualTo(content);
    }

    @Test
    void awsSdkv1() throws IOException {
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(LocalStackContainer.Service.S3))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .build();

        String bucketName = "test-s3";
        s3.createBucket(bucketName);
        log.info("버킷을 생성했습니다. bucketName={}", bucketName);

        String content = "Hello World";
        String key = "s3-key";
        s3.putObject(bucketName, key, content);
        log.info("파일을 업로드하였습니다. bucketName={}, key={}, content={}", bucketName, key, content);

        List<String> results = IOUtils.readLines(s3.getObject(bucketName, key).getObjectContent(), "utf-8");
        log.info("파일을 가져왔습니다. bucketName={}, key={}, results={}", bucketName, key, results);

        assertThat(results).hasSize(1);
        assertThat(results.get(0)).isEqualTo(content);
    }
}
