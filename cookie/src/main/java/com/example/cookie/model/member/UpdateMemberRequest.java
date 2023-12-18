package com.example.cookie.model.member;

public class UpdateMemberRequest {
    private String member_id;
    private String member_pw;
    private String member_nick;
    private String member_pho;
    private String member_mbti;

    // 생성자, getter, setter 메서드

    public UpdateMemberRequest() {
    }

    public UpdateMemberRequest(String member_id, String member_pw, String member_nick, String member_pho, String member_mbti, String updatedMember) {
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.member_nick = member_nick;
        this.member_pho = member_pho;
        this.member_mbti = member_mbti;
    }

    // getter, setter 메서드

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_pw() {
        return member_pw;
    }

    public void setMember_pw(String member_pw) {
        this.member_pw = member_pw;
    }

    public String getMember_nick() {
        return member_nick;
    }

    public void setMember_nick(String member_nick) {
        this.member_nick = member_nick;
    }

    public String getMember_pho() {
        return member_pho;
    }

    public void setMember_pho(String member_pho) {
        this.member_pho = member_pho;
    }

    public String getMember_mbti() {
        return member_mbti;
    }

    public void setMember_mbti(String member_mbti) {
        this.member_mbti = member_mbti;
    }
}

