package com.spring.spring1.service;

import com.spring.spring1.domain.Member;
import com.spring.spring1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입

    public Long join(Member member) {
        long start = System.currentTimeMillis();
        try{
        validateDuplicateMember(member); //중복이름x
        //저장
        memberRepository.save(member);
        return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs +"ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        //중복이름x
        Optional<Member> result = memberRepository.findByName(member.getName());

        result.ifPresent(member1 -> { //ifPresent 값이 있으면
            throw new IllegalStateException("존재하는 이름");
        });
    }

//전체회원조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try {
        return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs +"ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
