package edu.pkch.webclient.postbody.request;

import edu.pkch.webclient.postbody.AbstractPostRequest;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
public class DPostRequest extends AbstractPostRequest {
    private String firstName;
    private String lastName;

    @Builder
    public DPostRequest(LocalDateTime createdAt, String firstName, String lastName) {
        super(createdAt);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
