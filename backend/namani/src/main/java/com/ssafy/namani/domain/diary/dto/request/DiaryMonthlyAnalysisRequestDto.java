package com.ssafy.namani.domain.diary.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
public class DiaryMonthlyAnalysisRequestDto {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM", timezone = "Asia/Seoul")
    private Timestamp curMonth; // 연, 월 (Default Setting 현재 연 월)
}
