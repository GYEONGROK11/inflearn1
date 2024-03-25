package com.spring.spring1;

import com.spring.spring1.repository.JdbcMemberRepository;
import com.spring.spring1.repository.MemberRepository;
import com.spring.spring1.repository.MemoryMemberRepository;
import com.spring.spring1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();   //레포지토리 조립 나머지 코드는 그대로
        return new JdbcMemberRepository(dataSource);
    }
}
