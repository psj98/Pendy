package com.ssafy.namani.domain.member.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class MemberRegisterRequestDto {

    private String email;
    private String password;
    private String name;
    private Integer age;
    private Integer salary;
    List<String> accounts;
}
