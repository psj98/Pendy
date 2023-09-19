package com.ssafy.namani.domain.diary.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ToString
public class DiaryCreateByAIResponseDto {

    @NotNull
    private String content; // 일기 내용

    @NotNull
    private String comment; // 코멘트 분석

    @NotNull
    private Integer stampType; // 도장 종류

    @Builder
    public DiaryCreateByAIResponseDto(String content, String comment, Integer stampType) {
        this.content = content;
        this.comment = comment;
        this.stampType = stampType;
    }
}
