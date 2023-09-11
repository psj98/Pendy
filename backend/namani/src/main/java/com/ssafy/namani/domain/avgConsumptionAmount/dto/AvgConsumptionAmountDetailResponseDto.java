package com.ssafy.namani.domain.avgConsumptionAmount.dto;

import com.ssafy.namani.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AvgConsumptionAmountDetailResponseDto {

    @NotNull
    private Long id; // 평균 소비 금액 아이디

    @NotNull
    private Category category; // 카테고리 정보

    @NotNull
    private Integer age; // 연령대

    @NotNull
    private Integer salary; // 연봉대

    @NotNull
    private Integer peopleNum; // 인원수

    @NotNull
    private Integer sumAmount; // 총합

    @NotNull
    private Integer avgAmount; // 평균 소비 금액

    @NotNull
    private Date regDate; // 연월
}
