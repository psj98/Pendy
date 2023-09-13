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
     * @param curDate
     * @return
     * @throws BaseException
     */
    List<AvgConsumptionAmountDetailResponseDto> getAvgConsumptionAmountList(Integer age, Integer salary, Timestamp curDate) throws BaseException;

    /**
     * 매달 1일 모든 카테고리 + 모든 나이-소득 에 해당하는 avgConsumptionAmount 값을 생성하는 메서드
     */
    void registAvgConsumptionAmount();

    /**
     * 회원가입 시, 평균 소비 금액을 업데이트하는 메서드
     * 
     * @param accountNumber
     * @param age
     * @param salary
     * @throws BaseException
     */
    void updateAvgConsumptionAmountByMemberJoin(String accountNumber, Integer age, Integer salary) throws BaseException;
}
