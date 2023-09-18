package com.ssafy.namani.domain.diary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryRegistRequestDto {

    @NotNull
    private Long transactionId; // transaction 테이블 업데이트 id

    @NotNull
    private Integer emotionId; // 감정 점수
}
