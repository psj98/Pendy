package com.ssafy.namani.domain.goal.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Getter
@NoArgsConstructor
@ToString
public class GoalRegistMonthlyFeedbackRequestDto {

    @NotNull
    private HashMap<String, Integer[]> categoryData; // {카테고리명 : [소비 금액, 목표 금액]}

    @Builder
    public GoalRegistMonthlyFeedbackRequestDto(HashMap<String, Integer[]> categoryData) {
        this.categoryData = categoryData;
    }
}
