package com.ssafy.namani.domain.diary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.namani.domain.diary.dto.request.*;
import com.ssafy.namani.domain.diary.dto.response.DiaryDetailResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryListResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryMonthlyAnalysisResponseDto;
import com.ssafy.namani.global.response.BaseException;

import java.util.List;
import java.util.UUID;

public interface DiaryService {

    /**
     * 달력 조회 메서드
     *
     * @param memberId
     * @param diaryListRequestDto
     * @return DiaryListResponseDto
     * @throws BaseException
     */
    DiaryListResponseDto getCalendar(UUID memberId, DiaryListRequestDto diaryListRequestDto) throws BaseException;

    /**
     * 일기 생성 메서드
     *
     * @param memberId
     * @param diaryRegistRequestDtoList
     * @throws BaseException
     * @throws JsonProcessingException
     */
    void registDiary(UUID memberId, List<DiaryRegistRequestDto> diaryRegistRequestDtoList) throws BaseException, JsonProcessingException;

    /**
     * 일기 조회 메서드
     *
     * @param accessToken
     * @param diaryDetailRequestDto
     * @return DiaryDetailResponseDto
     * @throws BaseException
     */
    DiaryDetailResponseDto detailDiary(String accessToken, DiaryDetailRequestDto diaryDetailRequestDto) throws BaseException;

    /**
     * 일기 내용 수정 메서드
     *
     * @param id
     * @param diaryUpdateContentRequestDto
     * @throws BaseException
     */
    void updateDiary(Long id, DiaryUpdateContentRequestDto diaryUpdateContentRequestDto) throws BaseException;

    /**
     * 월간 분석 조회 메서드
     *
     * @param memberId
     * @param diaryMonthlyAnalysisRequestDto
     * @return DiaryMonthlyAnalysisResponseDto
     * @throws BaseException
     */
    DiaryMonthlyAnalysisResponseDto getMonthlyAnalysis(UUID memberId, DiaryMonthlyAnalysisRequestDto diaryMonthlyAnalysisRequestDto) throws BaseException;
}
