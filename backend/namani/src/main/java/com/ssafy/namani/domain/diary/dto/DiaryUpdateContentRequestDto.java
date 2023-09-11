package com.ssafy.namani.domain.diary.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DiaryUpdateContentRequestDto {

    @NotNull
    private String content; // 일기 내용
}
