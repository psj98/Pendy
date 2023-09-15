package com.ssafy.namani.domain.member.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDuplicationCheckRequestDto {
    String email;
}
