package com.ssafy.namani.domain.goal.dto.response;

import com.ssafy.namani.domain.avgConsumptionAmount.dto.response.AvgConsumptionAmountForThreeMonthResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class GoalDetailResponseDto {

    @NotNull
    private TotalGoalDetailResponseDto totalGoal; // 월별 목표

    @NotNull
    private List<GoalByCategoryDetailResponseDto> goalByCategoryList; // 카테고리 별 목표

    @NotNull
    private MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic; // 현재 달 월간 소비 통계

    @NotNull
    private List<MonthlyStatisticAmountByCategoryResponseDto> monthlyStatisticAvg; // 이전 3달간 월간 소비 통계

    @NotNull
    private List<AvgConsumptionAmountForThreeMonthResponseDto> avgConsumptionAmountAvg; // 이전 3달간 연령대 + 연봉대에 맞는 평균 소비 조회

    @Builder
    public GoalDetailResponseDto(TotalGoalDetailResponseDto totalGoal, List<GoalByCategoryDetailResponseDto> goalByCategoryList, MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic, List<MonthlyStatisticAmountByCategoryResponseDto> monthlyStatisticAvg, List<AvgConsumptionAmountForThreeMonthResponseDto> avgConsumptionAmountAvg) {
        this.totalGoal = totalGoal;
        this.goalByCategoryList = goalByCategoryList;
        this.monthlyStatistic = monthlyStatistic;
        this.monthlyStatisticAvg = monthlyStatisticAvg;
        this.avgConsumptionAmountAvg = avgConsumptionAmountAvg;
    }
}
