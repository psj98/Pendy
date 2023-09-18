package com.ssafy.namani.domain.diary.dto.response;

import com.ssafy.namani.domain.diary.entity.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryResponseDto {

    @NotNull
    private Diary diary;
}
