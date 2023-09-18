package com.ssafy.namani.domain.diary.dto;

import com.ssafy.namani.domain.diary.entity.Diary;
import com.ssafy.namani.domain.goal.entity.TotalGoal;
import com.ssafy.namani.domain.statistic.entity.DailyStatistic;
import com.ssafy.namani.domain.statistic.entity.MonthlyStatistic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryListResponseDto {

    @NotNull
    private Diary diary; // 일기 정보

    @NotNull
    private TotalGoal totalGoal; // 전체 목표

    @NotNull
    private DailyStatistic dailyStatistic; // 일간 통계

    @NotNull
    private MonthlyStatistic monthlyStatistic; // 월간 통계

    @NotNull
    private boolean newDailyTransaction; // 마지막으로 일기가 작성된 시각 ~ 현재 시각 사이 거래 내역이 있으면 true, 없으면 false
}
