package com.ssafy.namani.domain.member.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLoginResponseDto {
    private String accessToken;
    private String email;
    private String name;
    private Integer age;
    private Integer salary;


}
