package com.ssafy.namani.domain.diary.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Getter
@NoArgsConstructor
@ToString
public class DiaryCreateByAIRequestDto {

    @NotNull
    private Integer consumptionLimits; // 카테고리 별 목표

    @NotNull
    private HashMap<String, Integer[]> consumptionDetails;

    @Builder
    public DiaryCreateByAIRequestDto(Integer consumptionLimits, HashMap<String, Integer[]> consumptionDetails) {
        this.consumptionLimits = consumptionLimits;
        this.consumptionDetails = consumptionDetails;
    }
}
