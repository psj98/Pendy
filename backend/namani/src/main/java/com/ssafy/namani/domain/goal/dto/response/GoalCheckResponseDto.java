package com.ssafy.namani.domain.goal.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class GoalCheckResponseDto {

    @NotNull
    private boolean check; // 월별 목표가 존재 여부 체크

    @Builder
    public GoalCheckResponseDto(boolean check) {
        this.check = check;
    }
}
