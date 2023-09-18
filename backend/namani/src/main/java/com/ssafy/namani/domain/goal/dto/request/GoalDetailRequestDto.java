package com.ssafy.namani.domain.goal.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class GoalDetailRequestDto {

    @NotNull
    private Integer age; // 나이

    @NotNull
    private Integer salary; // 연봉

    @NotNull
    private Timestamp curDate; // 현재 연월
}
