package com.example.cookie.model.member;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;

        // Validate password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member_pw", "NotEmpty");
        if (member.getMember_pw() != null && !isPasswordValid(member.getMember_pw())) {
            errors.rejectValue("member_pw", "Invalid.memberForm.password");
        }
    }

    private boolean isPasswordValid(String password) {
        // 비밀번호가 4글자 이상이고, 영문, 숫자, 특수문자를 모두 포함하는지 확인
        return password.length() >= 4 &&
               password.matches(".*[a-zA-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?\\\\|].*");
    }
}