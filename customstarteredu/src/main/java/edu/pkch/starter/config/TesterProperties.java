package edu.pkch.starter.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = TesterProperties.TESTER_PREFIX)
public class TesterProperties {
    public static final String TESTER_PREFIX = "tester";

    /**
     * For tester name
     */
    private String name;

    public TesterProperties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
