package com.ssafy.namani.domain.goal.service;

import com.ssafy.namani.domain.goal.dto.request.GoalDetailRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalRegistRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalUpdateRequestDto;
import com.ssafy.namani.domain.goal.dto.response.GoalCheckResponseDto;
import com.ssafy.namani.domain.goal.dto.response.GoalDetailResponseDto;
import com.ssafy.namani.domain.goal.dto.response.TotalGoalDetailResponseDto;
import com.ssafy.namani.global.response.BaseException;

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
     */
    void updateGoal(UUID memberId, GoalUpdateRequestDto goalUpdateRequestDto) throws BaseException;
}
