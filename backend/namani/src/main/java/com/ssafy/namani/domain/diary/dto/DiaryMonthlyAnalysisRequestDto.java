package com.ssafy.namani.domain.diary.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
public class DiaryMonthlyAnalysisRequestDto {

    @NotNull
    private Date curMonth; // 연, 월 (Default Setting 현재 연 월)
}
