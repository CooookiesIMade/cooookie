package com.example.cookie.service;

import com.example.cookie.model.member.Member;
import com.example.cookie.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberMapper memberMapper;

    @Transactional
    public void saveMember(Member member) {
        memberMapper.saveMember(member);
    }

    public Member findMember(String member_id) {
        return memberMapper.findMember(member_id);
    }

    @Transactional
    public void updateMember(String member_id, Member updatedMember) {
        Member currentMember = memberMapper.findMember(member_id);

        if (currentMember != null) {
        	currentMember.setMember_pw(updatedMember.getMember_pw());
            currentMember.setMember_nick(updatedMember.getMember_nick());
            currentMember.setMember_pho(updatedMember.getMember_pho());
            currentMember.setMember_mbti(updatedMember.getMember_mbti());

            memberMapper.updateMember(currentMember);
        } else {
            log.error("Member not found for id: {}", member_id);
            throw new RuntimeException("Member not found for id: " + member_id);
        }
        
    }
}

