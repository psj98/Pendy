package com.ssafy.namani.domain.avgConsumptionAmount.dto;

import com.ssafy.namani.domain.category.entity.Category;
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
    private Category category; // 카테고리 정보

    @Builder
    public AvgConsumptionAmountDetailResponseDto(Double avgAmount, Category category) {
        this.avgAmount = avgAmount;
        this.category = category;
    }
}
