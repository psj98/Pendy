package com.ssafy.namani.domain.member.dto.response;


import com.ssafy.namani.domain.accountInfo.dto.response.AccountListResponseDto;
import lombok.*;

import java.util.List;

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
    private List<AccountListResponseDto> accountListResponseDtoList;


}
