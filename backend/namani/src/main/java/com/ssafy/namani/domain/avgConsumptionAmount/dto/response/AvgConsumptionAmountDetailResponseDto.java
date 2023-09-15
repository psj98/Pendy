package com.ssafy.namani.domain.avgConsumptionAmount.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AvgConsumptionAmountDetailResponseDto {

    @NotNull
    private Double avgAmount; // 평균 소비 금액

    @NotNull
    private Integer categoryId; // 카테고리 정보

    @NotNull
    private String categoryName; // 카테고리 명

    @Builder
    public AvgConsumptionAmountDetailResponseDto(Double avgAmount, Integer categoryId, String categoryName) {
        this.avgAmount = avgAmount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
