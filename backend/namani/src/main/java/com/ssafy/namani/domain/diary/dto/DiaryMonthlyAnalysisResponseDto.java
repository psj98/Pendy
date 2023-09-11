package com.ssafy.namani.domain.diary.dto;

import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import com.ssafy.namani.domain.goal.entity.TotalGoal;
import com.ssafy.namani.domain.statistic.entity.MonthlyStatistic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryMonthlyAnalysisResponseDto {

    @NotNull
    private TotalGoal totalGoal;

    @NotNull
    private GoalByCategory goalByCategory;

    @NotNull
    private MonthlyStatistic monthlyStatistic;

    @NotNull
    private boolean hasBeforeMonthlyGoal;

    @NotNull
    private boolean hasAfterMonthlyGoal;
}
