package com.ssafy.namani.domain.diary.dto.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ssafy.namani.domain.goal.dto.response.GoalByCategoryDetailResponseDto;
import com.ssafy.namani.domain.goal.dto.response.TotalGoalDetailResponseDto;
import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import com.ssafy.namani.domain.goal.entity.TotalGoal;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.domain.statistic.entity.MonthlyStatistic;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiaryMonthlyAnalysisResponseDto {

    @NotNull
    private TotalGoalDetailResponseDto totalGoal;

    @NotNull
    private List<GoalByCategoryDetailResponseDto> goalByCategory;

    @NotNull
    private MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic;

    @NotNull
    private boolean hasBeforeMonthlyGoal;

    @NotNull
    private boolean hasAfterMonthlyGoal;

    public DiaryMonthlyAnalysisResponseDto(TotalGoalDetailResponseDto totalGoal, List<GoalByCategoryDetailResponseDto> goalByCategory,
                                           MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic, boolean hasBeforeMonthlyGoal, boolean hasAfterMonthlyGoal) {
        this.totalGoal = totalGoal;
        this.goalByCategory = goalByCategory;
        this.monthlyStatistic = monthlyStatistic;
        this.hasBeforeMonthlyGoal = hasBeforeMonthlyGoal;
        this.hasAfterMonthlyGoal = hasAfterMonthlyGoal;
    }
}
