package com.ssafy.namani.domain.goal.dto.request;

import com.ssafy.namani.domain.goal.dto.response.GoalByCategoryDetailResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class GoalRegistRequestDto {

    @NotNull
    private Integer goalAmount; // 월 목표

    @NotNull
    private List<GoalByCategoryDetailResponseDto> goalByCategoryList; // 카테고리 별 목표
}
