package edu.pkch.starter.config;

import edu.pkch.starter.Tester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "tester", name = {"name"})
@EnableConfigurationProperties(TesterProperties.class)
public class TesterConfiguration {

    @Autowired
    private TesterProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public Tester tester() {
        return new Tester(properties.getName());
    }
}
