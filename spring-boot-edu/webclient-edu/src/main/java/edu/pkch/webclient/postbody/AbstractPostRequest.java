package edu.pkch.webclient.postbody;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public abstract class AbstractPostRequest {
    private LocalDateTime createdAt;

    public AbstractPostRequest(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
