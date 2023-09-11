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
    private int age;
    private int salary;
    List<String> accounts;

}
