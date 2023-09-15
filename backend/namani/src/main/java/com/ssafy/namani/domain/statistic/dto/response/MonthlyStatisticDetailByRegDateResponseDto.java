package com.ssafy.namani.domain.statistic.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class MonthlyStatisticDetailByRegDateResponseDto {

    @NotNull
    private Integer totalAmount;

    @NotNull
    private List<MonthlyStatisticAmountByCategoryResponseDto> amountByCategory;

    @Builder
    public MonthlyStatisticDetailByRegDateResponseDto(Integer totalAmount, List<MonthlyStatisticAmountByCategoryResponseDto> amountByCategory) {
        this.totalAmount = totalAmount;
        this.amountByCategory = amountByCategory;
    }
}
