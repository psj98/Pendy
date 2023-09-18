package com.ssafy.namani.domain.diary.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.namani.domain.diary.dto.request.DiaryDetailRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryListRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryMonthlyAnalysisRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryRegistRequestDto;
import com.ssafy.namani.domain.diary.dto.request.DiaryUpdateContentRequestDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryDetailResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryListResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryMonthlyAnalysisResponseDto;
import com.ssafy.namani.domain.diary.dto.response.DiaryResponseDto;
import com.ssafy.namani.domain.diary.entity.Diary;
import com.ssafy.namani.domain.diary.repository.DiaryRepository;
import com.ssafy.namani.domain.goal.dto.response.TotalGoalDetailResponseDto;
import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import com.ssafy.namani.domain.goal.entity.TotalGoal;
import com.ssafy.namani.domain.goal.repository.GoalByCategoryRepository;
import com.ssafy.namani.domain.goal.repository.TotalGoalRepository;
import com.ssafy.namani.domain.statistic.entity.MonthlyStatistic;
import com.ssafy.namani.domain.statistic.repository.MonthlyStatisticRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiaryServiceImpl implements DiaryService {
	private DiaryRepository diaryRepository;
	private TotalGoalRepository totalGoalRepository;
	private GoalByCategoryRepository goalByCategoryRepository;
	private MonthlyStatisticRepository monthlyStatisticRepository;

	@Autowired
	private DiaryServiceImpl(DiaryRepository diaryRepository,
		TotalGoalRepository totalGoalRepository,
		GoalByCategoryRepository goalByCategoryRepository,
		MonthlyStatisticRepository monthlyStatisticRepository) {
		this.diaryRepository = diaryRepository;
		this.totalGoalRepository = totalGoalRepository;
		this.goalByCategoryRepository = goalByCategoryRepository;
		this.monthlyStatisticRepository = monthlyStatisticRepository;
	}

	@Override
	public DiaryListResponseDto getCalendar(String accessToken, DiaryListRequestDto diaryListRequestDto) throws
		BaseException {
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
	public DiaryResponseDto registDiary(String accessToken,
		List<DiaryRegistRequestDto> diaryRegistRequestDtoList) throws BaseException {
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
	public DiaryDetailResponseDto detailDiary(String accessToken, DiaryDetailRequestDto diaryDetailRequestDto) throws
		BaseException {
		// accessToken decode => UUID
		Long id = diaryDetailRequestDto.getId();
		Timestamp regDate = diaryDetailRequestDto.getRegDate();

		Optional<Diary> diary = diaryRepository.findById(id);
		if (!diary.isPresent()) {
			// 일기 정보 없음 ERROR
		}

		// DailyStatistic 조회 => UUID와 regDate로 조회
		// 일간 통계 없음 ERROR

		// TotalGoal 조회 => UUID와 curMonth로 조회
		// 목표 없음 ERROR

		// GoalByCategory 조회 => totalGoalId로 조회
		// 목표 없음 ERROR

		return new DiaryDetailResponseDto(); // diary, dailyStatistic, goalAmount, goalByCategory
	}

	@Override
	public void updateDiary(Long id, DiaryUpdateContentRequestDto diaryUpdateContentRequestDto) throws
		BaseException {
		String content = diaryUpdateContentRequestDto.getContent();

		Optional<Diary> optionalDiary = diaryRepository.findById(id);

		if (!optionalDiary.isPresent()) {
			throw new BaseException(BaseResponseStatus.DIARY_NOT_FOUND);
		}
		Diary diary = optionalDiary.get();
		Diary diary1 = diary.toBuilder().
			content(content).
			build();

		diaryRepository.save(diary1);

	}

	@Override
	public DiaryMonthlyAnalysisResponseDto getMonthlyAnalysis(UUID memberId,
		DiaryMonthlyAnalysisRequestDto diaryMonthlyAnalysisRequestDto) throws BaseException {
		// accessToken decode => UUID
		Timestamp curMonth = diaryMonthlyAnalysisRequestDto.getCurMonth();
		log.debug("현재 연월 : " + curMonth);

		// TotalGoal 조회 => UUID와 curMonth로 조회
		Optional<TotalGoal> totalGoal = totalGoalRepository.findByCurDate(memberId, curMonth);
		// 목표 없음 ERROR
		if (totalGoal.isEmpty()) {
			throw new BaseException(BaseResponseStatus.TOTAL_GOAL_NOT_FOUND);
		}

		TotalGoalDetailResponseDto totalGoalDetailResponseDto = TotalGoalDetailResponseDto.builder()
			.id(totalGoal.get().getId())
			.goalAmount(totalGoal.get().getGoalAmount())
			.aiAnlaysis(totalGoal.get().getAiAnalysis())
			.build();
		// GoalByCategory 조회 => totalGoalId로 조회
		// 목표 없음 ERROR
		List<GoalByCategory> goalByCategories = new ArrayList<>();
		Long totalGoalId = totalGoal.get().getId();
		for (int i = 1; i <= 8; i++) {
			Optional<GoalByCategory> goalByCategory = goalByCategoryRepository.findByTotalGoalIdCategoryId(
				totalGoalId, i);
			goalByCategory.ifPresent(goalByCategories::add);
		}

		// MonthlyStatistic 조회 => UUID와 curMonth로 조회
		// 월간 통계 없음 ERROR
		Optional<List<MonthlyStatistic>> allByMemberIdRegDate = monthlyStatisticRepository.findAllByMemberIdRegDate(
			memberId, curMonth);
		if (allByMemberIdRegDate.isEmpty()) {
			throw new BaseException(BaseResponseStatus.MONTHLY_STATISTIC_NOT_FOUND);
		}
		// Query 생성 : select date_sub(curMonth, interval 1 month);
		// 이전 달 목표 체크
		// totalGoalRepository로 이전 달 목표 있는지 체크
		String beforeMonth = totalGoalRepository.findBeforeMonth(memberId, curMonth) + "-01 00:00:00.0";
		log.debug("이전 달 정보:" + beforeMonth);
		Timestamp beforeMonthTimestamp = Timestamp.valueOf(beforeMonth);
		log.debug("이전 달 정보:" + beforeMonthTimestamp);

		Boolean hasBeforeMonthlyGoal = true;
		Optional<TotalGoal> beforeMonthGoal = totalGoalRepository.findByCurDate(memberId, beforeMonthTimestamp);
		// 목표 없음 ERROR
		if (beforeMonthGoal.isEmpty()) {
			hasBeforeMonthlyGoal = false;
		}

		// Query 생성 : select date_add(curMonth, interval 1 month);
		// 다음 달 목표 체크
		// totalGoalRepository로 다음 달 목표 있는지 체크
		String afterMonth = totalGoalRepository.findAfterMonth(memberId, curMonth) + "-01 00:00:00.0";
		Timestamp afterMonthTimestamp = Timestamp.valueOf(afterMonth);
		log.debug("다음 달 정보:" + afterMonth);
		log.debug("다음 달 정보:" + afterMonthTimestamp);

		Boolean hasAfterMonthlyGoal = true;
		Optional<TotalGoal> afterMonthGoal = totalGoalRepository.findByCurDate(memberId, afterMonthTimestamp);
		// 목표 없음 ERROR
		if (afterMonthGoal.isEmpty()) {
			hasAfterMonthlyGoal = false;
		}

		return new DiaryMonthlyAnalysisResponseDto(
			totalGoalDetailResponseDto, goalByCategories,
			allByMemberIdRegDate.get(), hasBeforeMonthlyGoal,
			hasAfterMonthlyGoal); // totalGoal, goalByCategory, DailyStatistic, boolean, boolean
	}
}
