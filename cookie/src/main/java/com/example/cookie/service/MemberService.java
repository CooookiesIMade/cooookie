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
        Member findMember = memberMapper.findMember(member_id);
        return findMember;
    }

    // 사용자 정보 수정을 위한 메서드
    @Transactional
    public void updateMemberInfo(Member updatedMember) {
        memberMapper.updateMemberInfo(updatedMember);
    }

    @Transactional
    public void updatePassword(String memberId) {
        Member member = memberMapper.findMember(memberId);      
        }

    @Transactional
    public void updateMemberInfoWithoutIdChange(Member updatedMember) {
        // 아이디를 변경하지 않도록 업데이트
        memberMapper.updateMemberInfoWithoutIdChange(updatedMember);
    }
}
