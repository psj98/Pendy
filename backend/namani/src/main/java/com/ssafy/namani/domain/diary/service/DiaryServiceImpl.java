package com.ssafy.namani.domain.diary.service;

import com.ssafy.namani.domain.diary.dto.request.*;
import com.ssafy.namani.domain.diary.dto.response.DiaryDetailResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryListResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryMonthlyAnalysisResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryResponseDto;
import com.ssafy.namani.domain.diary.entity.Diary;
import com.ssafy.namani.domain.diary.repository.DiaryRepository;
import com.ssafy.namani.domain.goal.dto.response.GoalDetailResponseDto;
import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import com.ssafy.namani.domain.goal.entity.TotalGoal;
import com.ssafy.namani.domain.goal.repository.GoalByCategoryRepository;
import com.ssafy.namani.domain.goal.repository.TotalGoalRepository;
import com.ssafy.namani.domain.jwt.service.JwtService;
import com.ssafy.namani.domain.jwt.util.JwtUtil;
import com.ssafy.namani.domain.statistic.entity.DailyStatistic;
import com.ssafy.namani.domain.statistic.repository.DailyStatisticRepository;
import com.ssafy.namani.domain.statistic.service.StatisticService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiaryServiceImpl implements DiaryService {

    private DiaryService diaryService;
    private static DiaryService instance = new DiaryServiceImpl();
    private JwtService jwtService;
    private TotalGoalRepository totalGoalRepository;
    private GoalByCategoryRepository goalByCategoryRepository;
    private DailyStatisticRepository dailyStatisticRepository;

    private DiaryRepository diaryRepository;

    private DiaryServiceImpl() {
        diaryService = DiaryServiceImpl.getInstance();
    }

    public static DiaryService getInstance() {
        return instance;
    }

    @Override
    public DiaryListResponseDto getCalendar(String accessToken, DiaryListRequestDto diaryListRequestDto) throws BaseException {
        // accessToken decode => UUID

        Timestamp todayDate = diaryListRequestDto.getTodayDate();
        Timestamp todayMonth = diaryListRequestDto.getTodayMonth();


        // DiaryList - UUID와 todayMonth로 일기 정보 가져옴
        // 없으면 빈 배열 가져옴

        // TotalGoal - UUID와 todayMonth로 가져옴

        // DailyStatistic - UUID와 todayDate로 가져옴
        // 없으면 다 0으로 가져옴

        // MonthlyStatistic - UUID와 todayDate로 가져옴
        // 없으면 다 0으로 가져옴

        return new DiaryListResponseDto();
    }

    @Override
    public DiaryResponseDto registDiary(String accessToken, List<DiaryRegistRequestDto> diaryRegistRequestDtoList) throws BaseException {
        // accessToken decode => UUID

        // for문 진행
        for (DiaryRegistRequestDto diaryRegistRequestDto : diaryRegistRequestDtoList) {
            Integer emotionId = diaryRegistRequestDto.getEmotionId();
            Long transactionId = diaryRegistRequestDto.getTransactionId();

            // transactionRepository로 감정 등록
        }

        // AI에게 일기 생성 요청

        // 생성된 일기, 코멘트, 도장, 유저 아이디 등록
        // content
        // comment
        // stampType

//        Diary diary = Diary.builder()
//                .content()
//                .comment()
//                .stampType()
//                .build();

        // 저장
//        diaryRepository.save(diary);

//        return new DiaryResponseDto(diary);
        return new DiaryResponseDto();
    }

    @Override
    public DiaryDetailResponseDto detailDiary(String accessToken, DiaryDetailRequestDto diaryDetailRequestDto) throws BaseException {
        // accessToken decode => UUID
        UUID memberId = jwtService.getMemberIdFromToken(accessToken);
        Long diaryId = diaryDetailRequestDto.getId();
        Timestamp regDate = diaryDetailRequestDto.getRegDate();
        Diary diary;
        DailyStatistic dailyStatistic;
        TotalGoal totalGoal;
        GoalByCategory goalByCategory;
        Integer goalAmount;

        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        if (!optionalDiary.isPresent()) {
            throw new BaseException(BaseResponseStatus.DIARY_NOT_FOUND);
        }else{
            diary = optionalDiary.get();
        }

        // DailyStatistic 조회 => UUID와 regDate로 조회
        // 수정 필요
        Optional<DailyStatistic> optionalDailyStatistic = dailyStatisticRepository.findById(diaryId);
        // 일간 통계 없음 ERROR
        if(!optionalDailyStatistic.isPresent()){
            throw new BaseException(BaseResponseStatus.DAILY_STATISTIC_NOT_FOUND);
        }else {
            dailyStatistic = optionalDailyStatistic.get();
        }

        // TotalGoal 조회 => UUID와 curMonth로 조회
        Optional<TotalGoal> optionalTotalGoal = totalGoalRepository.findByCurDate(memberId, regDate);
        // 목표 없음 ERROR
        if(!optionalTotalGoal.isPresent()){
            throw new BaseException(BaseResponseStatus.TOTAL_GOAL_NOT_FOUND);
        }else{
            totalGoal = optionalTotalGoal.get();
        }

        goalAmount = totalGoal.getGoalAmount();

        // GoalByCategory 조회 => totalGoalId로 조회
        Optional<GoalByCategory> optionalGoalByCategory = goalByCategoryRepository.findById(optionalTotalGoal.get().getId());

        // 목표 없음 ERROR
        if(!optionalGoalByCategory.isPresent()){
            throw new BaseException(BaseResponseStatus.GOAL_BY_CATEGORY_NOT_FOUND);
        }else{
            goalByCategory = optionalGoalByCategory.get();
        }

        return new DiaryDetailResponseDto(diary, dailyStatistic, goalAmount, goalByCategory); // diary, dailyStatistic, goalAmount, goalByCategory
    }

    @Override
    public DiaryResponseDto updateDiary(Long id, DiaryUpdateContentRequestDto diaryUpdateContentRequestDto) throws BaseException {
        String content = diaryUpdateContentRequestDto.getContent();

        Optional<Diary> diary = diaryRepository.findById(id);
        if (!diary.isPresent()) {
            // 일기 정보 없음 ERROR
        }

        diaryRepository.updateDiary(id, content);

        diary = diaryRepository.findById(id); // 재확인

        return null;
    }

    @Override
    public DiaryMonthlyAnalysisResponseDto getMonthlyAnalysis(String accessToken, DiaryMonthlyAnalysisRequestDto diaryMonthlyAnalysisRequestDto) throws BaseException {
        // accessToken decode => UUID
        Timestamp curMonth = diaryMonthlyAnalysisRequestDto.getCurMonth();

        // TotalGoal 조회 => UUID와 curMonth로 조회
        // 목표 없음 ERROR

        // GoalByCategory 조회 => totalGoalId로 조회
        // 목표 없음 ERROR

        // DailyStatistic 조회 => UUID와 curMonth로 조회
        // 일간 통계 없음 ERROR

        // Query 생성 : select date_sub(curMonth, interval 1 month);
        // 이전 달 목표 체크
        // totalGoalRepository로 이전 달 목표 있는지 체크

        // Query 생성 : select date_add(curMonth, interval 1 month);
        // 다음 달 목표 체크
        // totalGoalRepository로 다음 달 목표 있는지 체크

        return new DiaryMonthlyAnalysisResponseDto(); // totalGoal, goalByCategory, DailyStatistic, boolean, boolean
    }
}
