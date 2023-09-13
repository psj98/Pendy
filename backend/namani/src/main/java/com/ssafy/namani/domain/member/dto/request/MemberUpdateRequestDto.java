package com.ssafy.namani.domain.member.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private String password;
    private Integer age;
    private Integer salary;
}
