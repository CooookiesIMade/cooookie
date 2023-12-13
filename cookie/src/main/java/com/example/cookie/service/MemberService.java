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
    public void updateMember(String member_id, Member modifiedMember) {
        Member currentMember = memberMapper.findMember(member_id);

        if (currentMember != null) {
            // 여기에서 modifiedMember의 필드들을 가져와서 currentMember에 업데이트합니다.
            currentMember.setMember_pw(modifiedMember.getMember_pw());
            currentMember.setMember_nick(modifiedMember.getMember_nick());
            currentMember.setMember_pho(modifiedMember.getMember_pho());
            currentMember.setMember_mbti(modifiedMember.getMember_mbti());

            memberMapper.updateMember(currentMember);
        } else {
            log.error("Member not found for id: {}", member_id);
            // 예외 처리 또는 경고 로그를 남기고 필요에 따라 예외를 던질 수 있습니다.
        }
    }
}

