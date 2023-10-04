package com.ssafy.namani.domain.goal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.namani.domain.goal.dto.request.GoalDetailRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalRegistRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalUpdateRequestDto;
import com.ssafy.namani.domain.goal.dto.response.GoalCheckResponseDto;
import com.ssafy.namani.domain.goal.dto.response.GoalDetailResponseDto;
import com.ssafy.namani.domain.goal.service.GoalService;
import com.ssafy.namani.domain.jwt.service.JwtService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
@Slf4j
public class GoalController {

    private final GoalService goalService;
    private final JwtService jwtService;
    private final BaseResponseService baseResponseService;

    /**
     * 로그인한 사용자 및 현재 연월에 해당하는 목표 정보가 있는지 체크하는 API
     *
     * @param token
     * @return BaseResponse<Object>
     */
    @GetMapping("/check")
    public BaseResponse<Object> checkGoal(@RequestHeader(value = "accessToken", required = false) String token) {
        try {
            // 토큰 정보 체크
            if (token == null || token.equals("")) {
                throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);
            }

            UUID memberId = jwtService.getMemberIdFromToken(token); // token으로 memberId 조회
            GoalCheckResponseDto goalCheckResponseDto = goalService.checkGoalByCurDate(memberId);

            return baseResponseService.getSuccessResponse(goalCheckResponseDto);
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }

    /**
     * 월별 목표를 등록하는 API
     *
     * @param token
     * @param goalRegistRequestDto
     * @return BaseResponse<Object>
     */
    @PostMapping("/regist")
    public BaseResponse<Object> registGoal(@RequestHeader(value = "accessToken", required = false) String token,
                                           @RequestBody GoalRegistRequestDto goalRegistRequestDto) {
        try {
            // 토큰 정보 체크
            if (token == null || token.equals("")) {
                throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);
            }

            UUID memberId = jwtService.getMemberIdFromToken(token); // token으로 memberId 조회
            goalService.registGoal(memberId, goalRegistRequestDto);
            return baseResponseService.getSuccessNoDataResponse();
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }

    /**
     * 목표 조회 메서드
     *
     * @param token
     * @param goalDetailRequestDto
     * @return BaseResponse<Object>
     */
    @PostMapping("/detail")
    public BaseResponse<Object> detailGoal(@RequestHeader(value = "accessToken", required = false) String token,
                                           @RequestBody GoalDetailRequestDto goalDetailRequestDto) {
        try {
            // 토큰 정보 체크
            if (token == null || token.equals("")) {
                throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);
            }

            UUID memberId = jwtService.getMemberIdFromToken(token); // token으로 memberId 조회
            GoalDetailResponseDto goalDetailResponseDto = goalService.detailGoal(memberId, goalDetailRequestDto);

            return baseResponseService.getSuccessResponse(goalDetailResponseDto);
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }

    /**
     * 목표 수정 메서드
     *
     * @param token
     * @param goalUpdateRequestDto
     * @return BaseResponse<Object>
     */
    @PutMapping
    public BaseResponse<Object> updateGoal(@RequestHeader(value = "accessToken", required = false) String token,
                                           @RequestBody GoalUpdateRequestDto goalUpdateRequestDto) {
        try {
            // 토큰 정보 체크
            if (token == null || token.equals("")) {
                throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);
            }

            UUID memberId = jwtService.getMemberIdFromToken(token); // token으로 memberId 조회
            goalService.updateGoal(memberId, goalUpdateRequestDto);

            return baseResponseService.getSuccessNoDataResponse();
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }

    /**
     * 월간 분석 피드백 생성 API
     * 
     * @return BaseResponse<Object>
     */
    @GetMapping("/monthly-feedback")
    public BaseResponse<Object> registMonthlyFeedback(@RequestHeader(value = "accessToken", required = false) String token) {
        try {
            // 토큰 정보 체크
            if (token == null || token.equals("")) {
                throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);
            }

            UUID memberId = jwtService.getMemberIdFromToken(token); // token으로 memberId 조회
            goalService.registMonthlyFeedback(memberId);

            return baseResponseService.getSuccessNoDataResponse();
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        } catch (JsonProcessingException e) {
            return baseResponseService.getFailureResponse(BaseResponseStatus.TOTAL_GOAL_JSON_PARSING_ERROR);
        }
    }
}
