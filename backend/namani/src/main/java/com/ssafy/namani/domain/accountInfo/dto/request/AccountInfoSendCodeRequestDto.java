package com.ssafy.namani.domain.accountInfo.dto.request;

import lombok.Getter;

@Getter
public class AccountInfoSendCodeRequestDto {
	private String bankCode;
	private String accountNumber;
}