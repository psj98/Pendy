package com.ssafy.namani.domain.diary.dto.response;

import com.ssafy.namani.domain.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiaryResponseDto {

    @NotNull
    private Diary diary;


    @Builder
    public DiaryResponseDto(Diary diary) {
        this.diary = diary;
    }
}
