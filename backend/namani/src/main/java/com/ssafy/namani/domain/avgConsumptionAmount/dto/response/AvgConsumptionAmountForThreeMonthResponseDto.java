package com.ssafy.namani.domain.avgConsumptionAmount.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ToString
public class AvgConsumptionAmountForThreeMonthResponseDto {

    @NotNull
    private Integer categoryId; // 카테고리 정보

    @NotNull
    private String categoryName; // 카테고리 명

    @NotNull
    private Integer amount; // 평균 소비 금액

    @Builder(toBuilder = true)
    public AvgConsumptionAmountForThreeMonthResponseDto(Integer categoryId, String categoryName, Integer amount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.amount = amount;
    }
}
