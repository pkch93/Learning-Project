package edu.pkch.jpaedu.multiple_relation;

import edu.pkch.jpaedu.JpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class MultipleArticleTest extends JpaTest {

    private EntityManager em;

    @BeforeEach
    void setUp() {
        em = createEntityManager();
    }

    @Test
    void init() {

    }
}