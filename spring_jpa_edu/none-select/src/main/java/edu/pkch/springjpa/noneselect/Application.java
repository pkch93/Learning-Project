package edu.pkch.springjpa.noneselect;

import edu.pkch.springjpa.noneselect.domain.Member;
import edu.pkch.springjpa.noneselect.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    private MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Member> members = IntStream.rangeClosed(1, 100)
                .mapToObj(idx -> new Member("name" + idx, idx))
                .collect(Collectors.toList());

        memberRepository.saveAll(members);
    }
}
