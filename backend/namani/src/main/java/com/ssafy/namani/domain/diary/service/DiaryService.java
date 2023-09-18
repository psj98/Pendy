package com.ssafy.namani.domain.diary.service;

import com.ssafy.namani.domain.diary.dto.request.DiaryDetailRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryListRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryMonthlyAnalysisRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryRegistRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryUpdateContentRequestDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryDetailResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryListResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryMonthlyAnalysisResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryResponseDto;
import com.ssafy.namani.global.response.BaseException;

import java.util.List;

public interface DiaryService {

    DiaryListResponseDto getCalendar(String accessToken, DiaryListRequestDto diaryListRequestDto) throws BaseException;

    DiaryResponseDto registDiary(String accessToken, List<DiaryRegistRequestDto> diaryRegistRequestDtoList) throws BaseException;

    DiaryDetailResponseDto detailDiary(String accessToken, DiaryDetailRequestDto diaryDetailRequestDto) throws BaseException;

    DiaryResponseDto updateDiary(Long id, DiaryUpdateContentRequestDto diaryUpdateContentRequestDto) throws BaseException;

    DiaryMonthlyAnalysisResponseDto getMonthlyAnalysis(String accessToken, DiaryMonthlyAnalysisRequestDto diaryMonthlyAnalysisRequestDto) throws BaseException;
}
