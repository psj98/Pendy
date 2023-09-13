package com.ssafy.namani.domain.accountInfo.dto.request;

import lombok.Getter;

@Getter
public class AccountInfoLoginRequestDto {
	private String accountNumber;
	private String bankCode;
	private Integer accountPassword;
}