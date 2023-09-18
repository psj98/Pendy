package com.ssafy.namani.domain.diary.service;

import com.ssafy.namani.domain.diary.dto.*;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface DiaryService {

    DiaryListResponseDto getCalendar(String accessToken, DiaryListRequestDto diaryListRequestDto) throws BaseException;

    DiaryResponseDto registDiary(String accessToken, List<DiaryRegistRequestDto> diaryRegistRequestDtoList) throws BaseException;

    DiaryDetailResponseDto detailDiary(String accessToken, DiaryDetailRequestDto diaryDetailRequestDto) throws BaseException;

    DiaryResponseDto updateDiary(Long id, DiaryUpdateContentRequestDto diaryUpdateContentRequestDto) throws BaseException;

    DiaryMonthlyAnalysisResponseDto getMonthlyAnalysis(String accessToken, DiaryMonthlyAnalysisRequestDto diaryMonthlyAnalysisRequestDto) throws BaseException;
}
