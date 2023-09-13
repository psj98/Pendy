package com.ssafy.namani.domain.transactionInfo.dto.request;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;

@Getter
public class TransactionInfoTodayListRequestDto {
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp lastRegDate; // 마지막 일기 생성 시각
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp curDate; // 현재 시각
}