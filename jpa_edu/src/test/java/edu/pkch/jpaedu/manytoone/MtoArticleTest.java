package edu.pkch.jpaedu.manytoone;

import edu.pkch.jpaedu.JpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

class MtoArticleTest extends JpaTest {

    private EntityManager em;

    @BeforeEach
    void setUp() {
        em = createEntityManager();
    }

    @Test
    void init() {

    }
}