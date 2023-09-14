package com.ssafy.namani.domain.goal.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class GoalByCategoryDetailResponseDto {

    @NotNull
    private Integer categoryId; // 카테고리 id

    @NotNull
    private Integer categoryGoalAmount; // 카테고리 별 목표
}
