package edu.pkch.springjpa.noneselect.service;

import edu.pkch.springjpa.noneselect.domain.Member;
import edu.pkch.springjpa.noneselect.domain.MemberRepository;
import edu.pkch.springjpa.noneselect.dto.MemberResponse;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private static final String REDIS_KEY = "key";

    private final MemberRepository memberRepository;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> readMembers() {
        List<MemberResponse> memberResponses = (List<MemberResponse>) valueOperations.get(REDIS_KEY);

        if (Objects.nonNull(memberResponses)) {
            return memberResponses;
        }

        List<Member> members = memberRepository.findAll();
        List<MemberResponse> foundedMembers = members.stream()
                .map(member -> new MemberResponse(member.getName(), member.getAge()))
                .collect(Collectors.toList());
        valueOperations.set(REDIS_KEY, foundedMembers);

        return foundedMembers;
    }
}
