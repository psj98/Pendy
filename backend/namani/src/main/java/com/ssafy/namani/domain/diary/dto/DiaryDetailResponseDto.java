package com.ssafy.namani.domain.diary.dto;

import com.ssafy.namani.domain.diary.entity.Diary;
import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import com.ssafy.namani.domain.statistic.entity.DailyStatistic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryDetailResponseDto {

    @NotNull
    private Diary diary;

    @NotNull
    private DailyStatistic dailyStatistic;

    @NotNull
    private Integer goalAmount;

    @NotNull
    private GoalByCategory goalByCategory;
}
