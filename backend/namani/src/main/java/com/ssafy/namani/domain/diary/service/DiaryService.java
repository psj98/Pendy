package com.ssafy.namani.domain.diary.service;

import com.ssafy.namani.domain.diary.dto.*;
import com.ssafy.namani.global.response.BaseException;

import java.util.List;
import java.util.UUID;

public interface DiaryService {

    DiaryListResponseDto getCalendar(UUID memberId, DiaryListRequestDto diaryListRequestDto) throws BaseException;

    void registDiary(UUID memberId, List<DiaryRegistRequestDto> diaryRegistRequestDtoList) throws BaseException;

    DiaryDetailResponseDto detailDiary(String accessToken, DiaryDetailRequestDto diaryDetailRequestDto) throws BaseException;

    void updateDiary(Long id, DiaryUpdateContentRequestDto diaryUpdateContentRequestDto) throws BaseException;

    DiaryMonthlyAnalysisResponseDto getMonthlyAnalysis(String accessToken, DiaryMonthlyAnalysisRequestDto diaryMonthlyAnalysisRequestDto) throws BaseException;
}
