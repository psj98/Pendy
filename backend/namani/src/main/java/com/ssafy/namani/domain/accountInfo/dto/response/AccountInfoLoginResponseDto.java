package com.ssafy.namani.domain.accountInfo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountInfoLoginResponseDto {
	private String accountNumber;
	private String bankCode;
	private Integer balance;

	@Builder
	public AccountInfoLoginResponseDto(String accountNumber, String bankCode, Integer balance) {
		this.accountNumber = accountNumber;
		this.bankCode = bankCode;
		this.balance = balance;
	}
}