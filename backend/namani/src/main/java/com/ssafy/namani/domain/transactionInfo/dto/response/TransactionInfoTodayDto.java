package com.ssafy.namani.domain.transactionInfo.dto.response;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
public class TransactionInfoTodayDto {
	private Long id;
	private Integer categoryId;
	private Integer emotionId;
	private String name;
	private Integer transactionAmount;
	private Timestamp tradeDate;
}

