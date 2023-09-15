package com.ssafy.namani.domain.statistic.controller;

import com.ssafy.namani.domain.jwt.service.JwtService;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.domain.statistic.service.StatisticService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 테스트용 통계 Controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;
    private final BaseResponseService baseResponseService;
    private final JwtService jwtService;

    @GetMapping("/monthly")
    public BaseResponse<Object> getMonthlyStatistic(@RequestHeader(value = "accessToken", required = false) String token) {
        try {
            // 토큰 정보 체크
            if (token == null || token.equals("")) {
                throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);
            }

            UUID memberId = jwtService.getMemberIdFromToken(token); // token으로 memberId 조회
            MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic = statisticService.getMonthlyStatisticByRegDate(memberId, Timestamp.valueOf(LocalDateTime.now()));

            return baseResponseService.getSuccessResponse(monthlyStatistic);
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }

    @GetMapping("/monthly-sum")
    public BaseResponse<Object> getMonthlyStatisticByThreeMonth(@RequestHeader(value = "accessToken", required = false) String token) {
        try {
            // 토큰 정보 체크
            if (token == null || token.equals("")) {
                throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);
            }

            UUID memberId = jwtService.getMemberIdFromToken(token); // token으로 memberId 조회
            List<MonthlyStatisticAmountByCategoryResponseDto> amountByCategory = statisticService.getMonthlyStatisticBeforeThreeMonth(memberId, Timestamp.valueOf(LocalDateTime.now()));

            return baseResponseService.getSuccessResponse(amountByCategory);
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }
}
