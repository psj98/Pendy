package com.ssafy.namani.domain.goal.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ToString
public class GoalRegistMonthlyFeedbackResponseDto {

    @NotNull
    private String message; // {카테고리명 : [소비 금액, 목표 금액]}

    @Builder
    public GoalRegistMonthlyFeedbackResponseDto(String message) {
        this.message = message;
    }
}
