package com.ssafy.namani.domain.goal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.namani.domain.goal.dto.request.GoalDetailRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalRegistRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalUpdateRequestDto;
import com.ssafy.namani.domain.goal.dto.response.GoalByCategoryDetailResponseDto;
import com.ssafy.namani.domain.goal.dto.response.GoalCheckResponseDto;
import com.ssafy.namani.domain.goal.dto.response.GoalDetailResponseDto;
import com.ssafy.namani.domain.goal.dto.response.TotalGoalDetailResponseDto;
import com.ssafy.namani.global.response.BaseException;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface GoalService {

    /**
     * 로그인한 사용자 및 현재 연월에 해당하는 목표 정보가 있는지 체크하는 메서드
     *
     * @param memberId
     * @return GoalCheckResponseDto
     * @throws BaseException
     */
    GoalCheckResponseDto checkGoalByCurDate(UUID memberId) throws BaseException;

    /**
     * 월별 목표 등록 메서드
     *
     * @param goalRegistRequestDto
     * @throws BaseException
     */
    void registGoal(UUID memberId, GoalRegistRequestDto goalRegistRequestDto) throws BaseException;

    /**
     * 월별 목표 조회 메서드
     *
     * @param memberId
     * @param curDate
     * @return TotalGoalDetailResponseDto
     * @throws BaseException
     */
    TotalGoalDetailResponseDto getTotalGoal(UUID memberId, Timestamp curDate) throws BaseException;

    /**
     * 카테고리 별 목표 조회 메서드
     *
     * @param totalGoalId
     * @return List<GoalByCategoryDetailResponseDto>
     * @throws BaseException
     */
    List<GoalByCategoryDetailResponseDto> getGoalByCategoryList(Long totalGoalId) throws BaseException;

    /**
     * 목표 조회 메서드
     *
     * @param memberId
     * @param goalDetailRequestDto
     * @return GoalDetailResponseDto
     * @throws BaseException
     */
    GoalDetailResponseDto detailGoal(UUID memberId, GoalDetailRequestDto goalDetailRequestDto) throws BaseException;

    /**
     * 목표 수정 메서드
     *
     * @param memberId
     * @param goalUpdateRequestDto
     * @throws BaseException
     */
    void updateGoal(UUID memberId, GoalUpdateRequestDto goalUpdateRequestDto) throws BaseException;

    /**
     * 월간 분석 피드백 생성 메서드 - 사용자 + 현재 달로 생성
     *
     * @throws BaseException
     * @throws JsonProcessingException
     */
    void registMonthlyFeedback(UUID memberId, Timestamp curDate) throws BaseException, JsonProcessingException;

    /**
     * 월간 분석 피드백 생성 메서드 - 매달 마지막 날에 자동 생성
     * 
     * @throws BaseException
     * @throws JsonProcessingException
     */
    void registMonthlyFeedback() throws BaseException, JsonProcessingException;
}
