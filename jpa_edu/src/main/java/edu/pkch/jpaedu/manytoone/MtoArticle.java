package edu.pkch.jpaedu.manytoone;

import javax.persistence.*;

@Entity
@Table(
        name = "mto_article",
        indexes = {
                @Index(name = "ix_article_title", columnList = "title")
        }
)
public class MtoArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Lob
    @Column
    private String content;
}
