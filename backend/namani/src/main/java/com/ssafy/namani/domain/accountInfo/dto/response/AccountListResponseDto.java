package com.ssafy.namani.domain.accountInfo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountListResponseDto {
    private String bankCode;
    private String accountNumber;


    @Builder
    public AccountListResponseDto (String bankCode, String accountNumber){
        this.bankCode = bankCode;
        this.accountNumber = accountNumber;
    }
}
