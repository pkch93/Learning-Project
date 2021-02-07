package edu.pkch.jpaedu.manytoone;

import javax.persistence.*;

@Entity
@Table(
        name = "mto_comment",
        indexes = {}
)
public class MtoComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mto_article_id", foreignKey = @ForeignKey(name = "fk_mto_article_mto_comment"))
    private MtoArticle mtoArticle;
}
