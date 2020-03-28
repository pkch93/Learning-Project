package edu.pkch.jpaedu.multiple_relation;

import javax.persistence.*;

@Entity
@Table(
        name = "multiple_comment",
        indexes = {}
)
public class MultipleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_article_comment"))
//    private MultipleArticle article;
}
