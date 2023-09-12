package com.ssafy.namani.domain.avgConsumptionAmount.service;

import com.ssafy.namani.domain.avgConsumptionAmount.dto.AvgConsumptionAmountDetailResponseDto;
import com.ssafy.namani.global.response.BaseException;

import java.sql.Timestamp;
import java.util.List;

public interface AvgConsumptionAmountService {

    /**
     * 나이-소득 구간, 특정 연월에 해당하는 평균 소비 정보 조회
     * 
     * @param age
     * @param salary
     * @param regDate
     * @return
     * @throws BaseException
     */
    List<AvgConsumptionAmountDetailResponseDto> getAvgConsumptionAmountInfo(Integer age, Integer salary, Timestamp regDate) throws BaseException;
}
