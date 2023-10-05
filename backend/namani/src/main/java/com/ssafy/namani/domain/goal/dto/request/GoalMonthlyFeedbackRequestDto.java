package com.ssafy.namani.domain.goal.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class GoalMonthlyFeedbackRequestDto {

    @NotNull
    private Timestamp curDate; // 조회할 연월
}
