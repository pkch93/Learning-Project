package edu.pkch.querydsl.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "member",
        indexes = {}
)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String password;

    @Column(length = 20)
    private int age;

    @Builder
    public Member(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }
}
