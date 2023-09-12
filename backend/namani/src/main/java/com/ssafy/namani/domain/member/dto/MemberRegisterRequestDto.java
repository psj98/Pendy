package com.ssafy.namani.domain.member.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
public class MemberRegisterRequestDto {

    private String email;
    private String password;
    private String name;
    private Integer age;
    private Integer salary;
    List<String> accounts;
}
