package com.ssafy.namani.domain.diary.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
public class DiaryDetailRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private Date regDate;
}
