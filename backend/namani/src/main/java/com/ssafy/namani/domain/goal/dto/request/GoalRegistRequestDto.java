package com.ssafy.namani.domain.goal.dto.request;

import com.ssafy.namani.domain.goal.dto.response.GoalByCategoryRegistResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class GoalRegistRequestDto {

    @NotNull
    private Integer goalAmount; // 월 목표

    @NotNull
    private List<GoalByCategoryRegistResponseDto> goalByCategoryList; // 카테고리 별 목표
}
