package com.ssafy.namani.domain.transactionInfo.dto.request;

import lombok.Getter;

@Getter
public class TransactionInfoRegistRequestDto {
	private String accountNumber;
	private Integer categoryId;
	private String transactionName;
	private Integer transactionAmount;
	private Integer transactionType; // 1 : 입금, 2 : 출금
	private Integer balance;

}