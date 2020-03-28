package edu.pkch.querydsl.practice;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.pkch.querydsl.domain.Member;
import edu.pkch.querydsl.domain.QMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static edu.pkch.querydsl.domain.QMember.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslPracticeTest {

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Member member = Member.builder()
                .name("pkch")
                .password("qwer1234")
                .age(28)
                .build();

        entityManager.persist(member);
    }

    @Test
    @DisplayName("jpql로 쿼리하기")
    void jpql() {
        String memberSelectQuery = "select m from Member m where m.name = :name";

        Member actual = entityManager.createQuery(memberSelectQuery, Member.class)
                .setParameter("name", "pkch")
                .getSingleResult();

        assertThat(actual).satisfies(result -> {
            assertThat(result.getName()).isEqualTo("pkch");
            assertThat(result.getPassword()).isEqualTo("qwer1234");
        });
    }

    @Test
    @DisplayName("querydsl로 쿼리하기")
    void querydsl() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember m = new QMember("m");

        Member actual = queryFactory
                .selectFrom(m)
                .where(m.name.eq("pkch"))
                .fetchOne();

        assertThat(actual).satisfies(result -> {
            assertThat(result.getName()).isEqualTo("pkch");
            assertThat(result.getPassword()).isEqualTo("qwer1234");
        });
    }

    @Test
    @DisplayName("querydsl로 projection 쿼리하기")
    void querydsl_projection() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QMember m = new QMember("m");

        String memberName = queryFactory
                .select(m.name)
                .from(m)
                .where(m.name.eq("pkch"))
                .fetchOne();

        assertThat(memberName).isEqualTo("pkch");
    }

    @Test
    @DisplayName("querydsl로 집합 쿼리하기")
    void querydsl_aggregate() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        List<Tuple> actual = queryFactory
                .select(member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min()
                )
                .from(member)
                .fetch();

        assertThat(actual).hasSize(1);
        Tuple result = actual.get(0);
        assertThat(result.get(member.count())).isEqualTo(1);
        assertThat(result.get(member.age.sum())).isEqualTo(28);
        assertThat(result.get(member.age.avg())).isEqualTo(28);
        assertThat(result.get(member.age.max())).isEqualTo(28);
        assertThat(result.get(member.age.min())).isEqualTo(28);
    }
}
