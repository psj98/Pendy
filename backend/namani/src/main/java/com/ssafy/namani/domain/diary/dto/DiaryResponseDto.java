package com.ssafy.namani.domain.diary.dto;

import com.ssafy.namani.domain.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryResponseDto {

    @NotNull
    private Diary diary;
}
