package com.example.reservation.service;

import com.example.reservation.persist.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String username) {
        return this.memberRepository.getByMemberId(username);
    }
}
