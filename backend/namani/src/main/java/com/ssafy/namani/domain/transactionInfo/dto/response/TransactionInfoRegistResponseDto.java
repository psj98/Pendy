package com.ssafy.namani.domain.transactionInfo.dto.response;

import lombok.Getter;

@Getter
public class TransactionInfoRegistResponseDto {
	private String balance;

	public TransactionInfoRegistResponseDto(String balance) {
		this.balance = balance;
	}
}
