package com.ssafy.namani.domain.diary.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
public class DiaryListRequestDto {

    @NotNull
    private Date todayDate; // 달력 부른 시간 - (연월일 시분초)

    @NotNull
    private Date todayMonth; // 현재 날짜 - 연월
}
