package edu.pkch.transaction.domain;

import javax.persistence.*;

@Entity
@Table(
        indexes = {}
)
public class Comment extends BaseDateEntity {

    @Lob
    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }
}
