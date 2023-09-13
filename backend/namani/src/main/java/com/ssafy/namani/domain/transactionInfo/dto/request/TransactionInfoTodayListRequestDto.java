package com.ssafy.namani.domain.transactionInfo.dto.request;

import java.sql.Timestamp;

import lombok.Getter;

@Getter
public class TransactionInfoTodayListRequestDto {
	private Timestamp lastRegDate; // 마지막 일기 생성 시각
	private Timestamp curDate; // 현재 시각
}