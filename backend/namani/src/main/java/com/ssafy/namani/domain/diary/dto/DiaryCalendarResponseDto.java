package com.ssafy.namani.domain.diary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class DiaryCalendarResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private Integer stampType;

    @NotNull
    private Timestamp regDate;

    @Builder
    public DiaryCalendarResponseDto(Long id, Integer stampType, Timestamp regDate) {
        this.id = id;
        this.stampType = stampType;
        this.regDate = regDate;
    }
}
