package com.ssafy.namani.domain.diary.dto.response;

import com.ssafy.namani.domain.goal.dto.response.GoalByCategoryDetailResponseDto;
import com.ssafy.namani.domain.goal.dto.response.TotalGoalDetailResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.DailyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class DiaryListResponseDto {

    @NotNull
    private List<DiaryCalendarResponseDto> diaryList; // 일기 정보

    @NotNull
    private TotalGoalDetailResponseDto totalGoal; // 전체 목표

    @NotNull
    private List<GoalByCategoryDetailResponseDto> goalByCategoryList;

    @NotNull
    private DailyStatisticDetailByRegDateResponseDto dailyStatistic; // 일간 통계

    @NotNull
    private MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic; // 월간 통계

    @NotNull
    private boolean newDailyTransaction; // 마지막으로 일기가 작성된 시각 ~ 현재 시각 사이 거래 내역이 있으면 true, 없으면 false

    @Builder
    public DiaryListResponseDto(List<DiaryCalendarResponseDto> diaryList,
        TotalGoalDetailResponseDto totalGoal, List<GoalByCategoryDetailResponseDto> goalByCategoryList,
        DailyStatisticDetailByRegDateResponseDto dailyStatistic, MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic,
        boolean newDailyTransaction) {
        this.diaryList = diaryList;
        this.totalGoal = totalGoal;
        this.goalByCategoryList = goalByCategoryList;
        this.dailyStatistic = dailyStatistic;
        this.monthlyStatistic = monthlyStatistic;
        this.newDailyTransaction = newDailyTransaction;
    }
}
