package com.ssafy.namani.domain.diary.dto.response;

import com.ssafy.namani.domain.diary.entity.Diary;
import com.ssafy.namani.domain.goal.dto.response.GoalByCategoryDetailResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.DailyStatisticDetailByRegDateResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class DiaryDetailResponseDto {

    @NotNull
    private Diary diary;

    @NotNull
    private DailyStatisticDetailByRegDateResponseDto dailyStatistic;

    @NotNull
    private Integer goalAmount;

    @NotNull
    private List<GoalByCategoryDetailResponseDto> goalByCategory;

    @Builder
    public DiaryDetailResponseDto(Diary diary, DailyStatisticDetailByRegDateResponseDto dailyStatistic, Integer goalAmount, List<GoalByCategoryDetailResponseDto> goalByCategory) {
        this.diary = diary;
        this.dailyStatistic = dailyStatistic;
        this.goalAmount = goalAmount;
        this.goalByCategory = goalByCategory;
    }
}
