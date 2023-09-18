package com.ssafy.namani.domain.avgConsumptionAmount.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AvgConsumptionAmountForThreeMonthRequestDto {

    @NotNull
    private Integer age; // 나이

    @NotNull
    private Integer salary; // 연봉
}
