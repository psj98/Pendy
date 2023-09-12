package com.ssafy.namani.domain.accountInfo.dto.request;

import lombok.Getter;

@Getter
public class AccountInfoRegistRequestDto {
	private String accountNumber;
	private String bankCode;
	private Integer accountPassword;
	private Integer balance;
}