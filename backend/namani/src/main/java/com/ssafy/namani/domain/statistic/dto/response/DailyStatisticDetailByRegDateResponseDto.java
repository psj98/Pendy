package com.ssafy.namani.domain.statistic.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class DailyStatisticDetailByRegDateResponseDto {

    @NotNull
    private Integer totalAmount;

    @NotNull
    private List<DailyStatisticAmountByCategoryResponseDto> amountByCategory;

    @Builder
    public DailyStatisticDetailByRegDateResponseDto(Integer totalAmount, List<DailyStatisticAmountByCategoryResponseDto> amountByCategory) {
        this.totalAmount = totalAmount;
        this.amountByCategory = amountByCategory;
    }
}
