package com.example.cookie.model.member;

import lombok.Data;

@Data
public class UpdateMemberRequest {
    private String member_id;
    private String member_pw;
    private String member_nick;
    private String member_pho;
    private String member_mbti;

   

    public UpdateMemberRequest(String member_id, String member_pw, String member_nick, String member_pho, String member_mbti, String updatedMember) {
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.member_nick = member_nick;
        this.member_pho = member_pho;
        this.member_mbti = member_mbti;
    }

    
}
