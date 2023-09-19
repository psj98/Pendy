package com.ssafy.namani.domain.goal.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class TotalGoalDetailResponseDto {

	@NotNull
	private Long id; // 월간 목표 Id

	@NotNull
	private Integer goalAmount; // 월간 목표 소비 금액

	private String aiAnlaysis; // ai일기 분석 텍스트

	@Builder
	public TotalGoalDetailResponseDto(Long id, Integer goalAmount, String aiAnlaysis) {
		this.id = id;
		this.goalAmount = goalAmount;
		this.aiAnlaysis = aiAnlaysis;
	}
}
