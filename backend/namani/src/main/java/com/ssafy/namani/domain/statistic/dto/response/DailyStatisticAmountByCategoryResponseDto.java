package com.ssafy.namani.domain.statistic.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DailyStatisticAmountByCategoryResponseDto {
    @NotNull
    private Integer categoryId; // 카테고리 id

    @NotNull
    private String categoryName; // 카테고리 명

    @NotNull
    private Integer amount; // 통계값

    @Builder
    public DailyStatisticAmountByCategoryResponseDto(Integer categoryId, String categoryName, Integer amount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.amount = amount;
    }
}
