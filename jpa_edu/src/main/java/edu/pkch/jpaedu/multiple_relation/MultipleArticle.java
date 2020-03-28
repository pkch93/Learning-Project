package edu.pkch.jpaedu.multiple_relation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "multiple_article",
        indexes = {}
)
public class MultipleArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Lob
    @Column
    private String content;

    @OneToMany
    private List<MultipleComment> comments = new ArrayList<>();
}
