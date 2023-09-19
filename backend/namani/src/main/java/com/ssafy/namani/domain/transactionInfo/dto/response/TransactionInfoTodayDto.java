package com.ssafy.namani.domain.transactionInfo.dto.response;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransactionInfoTodayDto {
	private Long id;
	private Integer categoryId;
	private Integer emotionId;
	private String name;
	private Integer transactionAmount;
	private Timestamp tradeDate;

	public TransactionInfoTodayDto(Long id, Integer categoryId, Integer emotionId, String name,
		Integer transactionAmount,
		Timestamp tradeDate) {
		this.id = id;
		this.categoryId = categoryId;
		this.emotionId = emotionId;
		this.name = name;
		this.transactionAmount = transactionAmount;
		this.tradeDate = tradeDate;
	}
}

