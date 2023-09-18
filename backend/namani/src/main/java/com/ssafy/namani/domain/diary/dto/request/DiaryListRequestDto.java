package com.ssafy.namani.domain.diary.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
public class DiaryListRequestDto {

    @NotNull
    private Timestamp todayDate; // 달력 부른 시간 - (연월일 시분초)

    @NotNull
    private Timestamp todayMonth; // 현재 날짜 - 연월
}
