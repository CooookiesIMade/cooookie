package com.example.cookie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.cookie.model.file.AttachedFile;
import com.example.cookie.model.member.Member;
import com.example.cookie.model.rent.RentPlace;
import com.example.cookie.repository.MemberMapper;
import com.example.cookie.util.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

	private final MemberMapper memberMapper;
	private final FileService fileService;
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Transactional
	public void saveMember(Member member, MultipartFile file) {
		memberMapper.saveMember(member);
		if(file != null && file.getSize() > 0) {
	     	//첨부파일을 서버에 저장
	     	AttachedFile attachedFile = fileService.saveFile(file);
	     	//첨부파일 내용을 데이터베이스에 저장
	     	memberMapper.saveFile(attachedFile);
		   }
	}

	public Member findMember(String member_id) {
		Member findMember = memberMapper.findMember(member_id);
		return findMember;
	}
	
	public List<RentPlace> findRentPlaces(String member_id){
		return memberMapper.findRentPlaces(member_id);
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
