package com.ssafy.namani.domain.goal.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class GoalByCategoryDetailResponseDto {

    @NotNull
    private Integer categoryId; // 카테고리 id

    @NotNull
    private String categoryName; // 카테고리 명

    @NotNull
    private Integer categoryGoalAmount; // 카테고리 별 목표

    @Builder
    public GoalByCategoryDetailResponseDto(Integer categoryId, String categoryName, Integer categoryGoalAmount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryGoalAmount = categoryGoalAmount;
    }
}
