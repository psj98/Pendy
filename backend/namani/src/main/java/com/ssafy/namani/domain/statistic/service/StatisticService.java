package com.ssafy.namani.domain.statistic.service;

import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.global.response.BaseException;

import java.sql.Timestamp;
import java.util.UUID;

public interface StatisticService {

    /**
     * 로그인 한 사용자의 아이디 + 특정 날짜에 해당하는 월간 통계 정보 조회
     * 
     * @param memberId
     * @param curDate
     * @return MonthlyStatisticDetailByRegDateResponseDto
     * @throws BaseException
     */
    MonthlyStatisticDetailByRegDateResponseDto getMonthlyStatisticByRegDate(UUID memberId, Timestamp curDate) throws BaseException;
}
