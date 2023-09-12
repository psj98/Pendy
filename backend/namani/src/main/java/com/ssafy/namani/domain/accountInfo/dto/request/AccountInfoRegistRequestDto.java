package com.ssafy.namani.domain.accountInfo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountInfoRegistRequestDto {
	private String accountNumber;
	private String bankCode;
	private Integer accountPassword;
	private Integer balance;
}