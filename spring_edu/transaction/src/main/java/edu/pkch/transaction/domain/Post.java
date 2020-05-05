package edu.pkch.transaction.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(
        indexes = {}
)
public class Post extends BaseDateEntity {

    @Column(length = 100)
    private String title;

    @Lob
    @Column
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
