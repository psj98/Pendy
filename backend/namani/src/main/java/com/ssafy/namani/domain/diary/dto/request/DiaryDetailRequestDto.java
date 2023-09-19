package com.ssafy.namani.domain.diary.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class DiaryDetailRequestDto {

    @NotNull
    private Long id;

    @NotNull
    private Timestamp regDate;
}
