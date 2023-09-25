package com.ssafy.namani.domain.goal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.namani.domain.avgConsumptionAmount.dto.response.AvgConsumptionAmountForThreeMonthResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.service.AvgConsumptionAmountService;
import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.domain.goal.dto.request.GoalDetailRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalRegistMonthlyFeedbackRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalRegistRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalUpdateRequestDto;
import com.ssafy.namani.domain.goal.dto.response.*;
import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import com.ssafy.namani.domain.goal.entity.TotalGoal;
import com.ssafy.namani.domain.goal.repository.GoalByCategoryRepository;
import com.ssafy.namani.domain.goal.repository.TotalGoalRepository;
import com.ssafy.namani.domain.member.entity.Member;
import com.ssafy.namani.domain.member.repository.MemberRepository;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.domain.statistic.service.StatisticService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class GoalServiceImpl implements GoalService {

    private final TotalGoalRepository totalGoalRepository;
    private final GoalByCategoryRepository goalByCategoryRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final StatisticService statisticService;
    private final AvgConsumptionAmountService avgConsumptionAmountService;

    /**
     * 로그인한 사용자 및 현재 연월에 해당하는 목표 정보가 있는지 체크하는 메서드
     *
     * @param memberId
     * @return GoalCheckResponseDto
     * @throws BaseException
     */
    @Override
    public GoalCheckResponseDto checkGoalByCurDate(UUID memberId) throws BaseException {
        Optional<Member> memberOptional = memberRepository.findById(memberId); // 사용자 정보

        // 사용자 정보 체크
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER);
        }

        // 로그인한 사용자 및 현재 연월에 해당하는 목표 정보가 있는지 체크
        Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findByCurDate(memberId, Timestamp.valueOf(LocalDateTime.now()));

        if (totalGoalOptional.isPresent()) { // 존재하는 경우, "존재하여 생성할 수 없다" & 메인 페이지
            return GoalCheckResponseDto.builder()
                    .check(true).build();
        } else { // 존재하지 않는 경우, 생성 페이지
            return GoalCheckResponseDto.builder()
                    .check(false).build();
        }
    }

    /**
     * 월별 목표 등록 메서드
     *
     * @param memberId
     * @param goalRegistRequestDto
     * @throws BaseException
     */
    @Override
    public void registGoal(UUID memberId, GoalRegistRequestDto goalRegistRequestDto) throws BaseException {
        log.info("member"+ memberId);
        log.info("dto" + goalRegistRequestDto.getGoalByCategoryList().toString());
        Optional<Member> memberOptional = memberRepository.findById(memberId);



        // 사용자 정보 체크
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER);
        }

        Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findByCurDate(memberId, Timestamp.valueOf(LocalDateTime.now()));

        // 목표 정보 체크
//        if (totalGoalOptional.isPresent()) {
//            System.out.println("testtesttest");
//            throw new BaseException(BaseResponseStatus.TOTAL_GOAL_IS_ALREADY_PRESENT);
//        }

        // 월별 목표 정보 저장
        Integer goalAmount = goalRegistRequestDto.getGoalAmount();
        List<GoalByCategoryRegistResponseDto> goalByCategoryList = goalRegistRequestDto.getGoalByCategoryList();
        TotalGoal newTotalGoal = totalGoalOptional.get();

        TotalGoal totalGoal = newTotalGoal.toBuilder()
                .member(memberOptional.get())
                .goalAmount(goalAmount)
                .goalDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        log.info(totalGoal.toString());
        totalGoalRepository.save(totalGoal);

        log.info("success");

        // 카테고리 별로 목표 저장
        for (GoalByCategoryRegistResponseDto goalByCategoryRegistResponseDto : goalByCategoryList) {
            Optional<Category> categoryOptional = categoryRepository.findById(goalByCategoryRegistResponseDto.getCategoryId());

            // 카테고리 정보 체크
            if (!categoryOptional.isPresent()) {
                throw new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND);
            }

            GoalByCategory goalByCategory = GoalByCategory.builder()
                    .totalGoal(totalGoal)
                    .category(categoryOptional.get())
                    .categoryGoalAmount(goalByCategoryRegistResponseDto.getCategoryGoalAmount())
                    .build();

            goalByCategoryRepository.save(goalByCategory);
        }
    }

    /**
     * 월별 목표 조회
     *
     * @param memberId
     * @param curDate
     * @return TotalGoalDetailResponseDto
     * @throws BaseException
     */
    @Override
    public TotalGoalDetailResponseDto getTotalGoal(UUID memberId, Timestamp curDate) {
        Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findByCurDate(memberId, curDate);
        Member member = memberRepository.findById(memberId).get();

        // 월별 목표가 없으면, 초기값을 0으로 생성
        if (!totalGoalOptional.isPresent()) {
            TotalGoal totalGoal = TotalGoal.builder()
                    .member(member)
                    .goalAmount(0)
                    .goalDate(curDate)
                    .build();

            totalGoalRepository.save(totalGoal);
        }

        // 반환할 데이터 저장
        TotalGoal newTotalGoal = totalGoalRepository.findByCurDate(memberId, curDate).get();
        TotalGoalDetailResponseDto totalGoal = TotalGoalDetailResponseDto.builder()
                .id(newTotalGoal.getId())
                .goalAmount(newTotalGoal.getGoalAmount())
                .build();

        return totalGoal;
    }

    /**
     * 카테고리 별 목표 조회
     *
     * @param totalGoalId
     * @return List<GoalByCategoryDetailResponseDto>
     * @throws BaseException
     */
    @Override
    public List<GoalByCategoryDetailResponseDto> getGoalByCategoryList(Long totalGoalId) {
        List<GoalByCategoryDetailResponseDto> goalByCategoryList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        TotalGoal totalGoal = totalGoalRepository.findById(totalGoalId).get();

        // 카테고리 별 목표가 없는 경우, 초기값을 0으로 생성
        Optional<List<GoalByCategory>> goalByCategoryListOptional = goalByCategoryRepository.findAllByTotalGoalId(totalGoalId);
        if (goalByCategoryListOptional.isEmpty()) { // 없으면 생성
            for (Category category : categoryList) {
                GoalByCategory goalByCategory = GoalByCategory.builder()
                        .category(category)
                        .totalGoal(totalGoal)
                        .categoryGoalAmount(0)
                        .build();

                goalByCategoryRepository.save(goalByCategory);
            }
        }

        // 반환할 데이터 저장
        List<GoalByCategory> goalByCategories = goalByCategoryRepository.findAllByTotalGoalId(totalGoalId).get();
        for (GoalByCategory goalByCategory : goalByCategories) {
            GoalByCategoryDetailResponseDto goalByCategoryDetailResponseDto =
                    GoalByCategoryDetailResponseDto.builder()
                            .categoryId(goalByCategory.getCategory().getId())
                            .categoryName(goalByCategory.getCategory().getName())
                            .categoryGoalAmount(goalByCategory.getCategoryGoalAmount())
                            .build();

            goalByCategoryList.add(goalByCategoryDetailResponseDto);
        }

        return goalByCategoryList;
    }

    /**
     * 목표 조회 메서드
     *
     * @param memberId
     * @param goalDetailRequestDto
     * @return GoalDetailResponseDto
     * @throws BaseException
     */
    @Override
    public GoalDetailResponseDto detailGoal(UUID memberId, GoalDetailRequestDto goalDetailRequestDto) throws BaseException {
        Integer age = goalDetailRequestDto.getAge();
        Integer salary = goalDetailRequestDto.getSalary();
        Timestamp curDate = goalDetailRequestDto.getCurDate();

        /* 월별 목표 조회 */
        TotalGoalDetailResponseDto totalGoal = getTotalGoal(memberId, curDate);
        System.out.println(totalGoal.getId());

        /* 카테고리 별 목표 조회 */
        List<GoalByCategoryDetailResponseDto> goalByCategoryList = getGoalByCategoryList(totalGoal.getId());

        /* 현재 달 월간 소비 통계 조회 */
        MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic = statisticService.getMonthlyStatisticByRegDate(memberId, curDate);

        /* 이전 3달간 월간 소비 통계 조회 */
        List<MonthlyStatisticAmountByCategoryResponseDto> monthlyStatisticAvg = statisticService.getMonthlyStatisticAvgForThreeMonth(memberId, curDate);

        /* 이전 3달간 연령대 + 연봉대에 맞는 평균 소비 조회 */
        List<AvgConsumptionAmountForThreeMonthResponseDto> avgConsumptionAmountAvg = avgConsumptionAmountService.getAvgConsumptionAmountForThreeMonth(age, salary, curDate);

        /* 목표 조회 데이터 정보 저장 및 반환 */
        GoalDetailResponseDto goalDetailResponseDto = GoalDetailResponseDto.builder()
                .totalGoal(totalGoal)
                .goalByCategoryList(goalByCategoryList)
                .monthlyStatistic(monthlyStatistic)
                .monthlyStatisticAvg(monthlyStatisticAvg)
                .avgConsumptionAmountAvg(avgConsumptionAmountAvg)
                .build();

        return goalDetailResponseDto;
    }

    /**
     * 목표 수정 메서드
     *
     * @param memberId
     * @param goalUpdateRequestDto
     * @throws BaseException
     */
    @Override
    public void updateGoal(UUID memberId, GoalUpdateRequestDto goalUpdateRequestDto) throws BaseException {
        Long totalGoalId = goalUpdateRequestDto.getId();
        Integer goalAmount = goalUpdateRequestDto.getGoalAmount();

        Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findById(totalGoalId);

        // 월별 목표 정보 체크
        if (!totalGoalOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.TOTAL_GOAL_NOT_FOUND);
        }

        // 월별 목표 수정
        TotalGoal totalGoal = totalGoalOptional.get();
        TotalGoal newTotalGoal = totalGoal.toBuilder()
                .goalAmount(goalAmount)
                .build();

        totalGoalRepository.save(newTotalGoal);

        // 카테고리 별 목표 수정
        List<GoalByCategoryUpdateResponseDto> goalByCategoryList = goalUpdateRequestDto.getGoalByCategoryList();
        for (GoalByCategoryUpdateResponseDto goalByCategoryUpdateResponseDto : goalByCategoryList) {
            Integer categoryId = goalByCategoryUpdateResponseDto.getCategoryId();
            Integer categoryGoalAmount = goalByCategoryUpdateResponseDto.getCategoryGoalAmount();

            Optional<Category> category = categoryRepository.findById(categoryId);

            // 카테고리 정보 체크
            if (!category.isPresent()) {
                throw new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND);
            }

            Optional<GoalByCategory> goalByCategoryOptional = goalByCategoryRepository.findByTotalGoalIdCategoryId(totalGoalId, categoryId);

            // 카테고리 별 목표 체크
            if (!goalByCategoryOptional.isPresent()) {
                throw new BaseException(BaseResponseStatus.GOAL_BY_CATEGORY_NOT_FOUND);
            }

            // 카테고리 별 목표 수정
            GoalByCategory goalByCategory = goalByCategoryOptional.get();
            GoalByCategory newGoalByCategory = goalByCategory.toBuilder()
                    .categoryGoalAmount(categoryGoalAmount)
                    .build();

            goalByCategoryRepository.save(newGoalByCategory);
        }
    }

    /**
     * 월간 분석 피드백 생성 메서드
     *
     * @throws BaseException
     * @throws JsonProcessingException
     */
    @Scheduled(cron = "0 59 23 L * *")
    public void registMonthlyFeedback() throws BaseException, JsonProcessingException {
        Timestamp curDate = Timestamp.valueOf(LocalDateTime.now());
        LinkedHashMap<String, Integer[]> categoryData = new LinkedHashMap<>();
        List<Member> memberList = memberRepository.findAll();

        for (Member member : memberList) {
            // 사용자 + 현재 월에 해당하는 카테고리 별 목표 가져오기
            UUID memberId = member.getId();
            Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findByCurDate(memberId, curDate);
            if (!totalGoalOptional.isPresent()) {
                continue;
            }

            // 월간 분석이 생성되어 있는 경우, 스킵 (테스트용)
            TotalGoal totalGoal = totalGoalOptional.get();
            if (totalGoal.getAiAnalysis() != null) {
                continue;
            }

            List<GoalByCategoryDetailResponseDto> goalByCategoryList = getGoalByCategoryList(totalGoal.getId());

            // 사용자 + 현재 월에 해당하는 카테고리 별 소비 금액 가져오기
            statisticService.checkDailyStatistic(memberId, curDate);
            MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic = statisticService.getMonthlyStatisticByRegDate(memberId, curDate);
            List<MonthlyStatisticAmountByCategoryResponseDto> amountByCategory = monthlyStatistic.getAmountByCategory();

            for (int i = 0; i < 8; i++) {
                Integer[] amount = {amountByCategory.get(i).getAmount(), goalByCategoryList.get(i).getCategoryGoalAmount()};
                categoryData.put(amountByCategory.get(i).getCategoryName(), amount);
            }

            /* -------------- AI에게 월간 분석 요청 -------------- */
            String url = "http://localhost:8000/ml/create-report"; // 파이썬 요청 url
            RestTemplate restTemplate = new RestTemplate();

            /* -------------- 데이터 전달 및 생성된 월간 분석 데이터 반환 -------------- */
            GoalRegistMonthlyFeedbackRequestDto goalRegistMonthlyFeedbackRequestDto =
                    GoalRegistMonthlyFeedbackRequestDto.builder()
                            .categoryData(categoryData)
                            .build();
            String response = restTemplate.postForObject(url, goalRegistMonthlyFeedbackRequestDto, String.class);

            // GoalRegistMonthlyFeedbackResponseDto로 매핑
            ObjectMapper mapper = new ObjectMapper();
            GoalRegistMonthlyFeedbackResponseDto goalRegistMonthlyFeedbackResponseDto = mapper.readValue(response, GoalRegistMonthlyFeedbackResponseDto.class);

            // AI 분석 피드백 저장
            TotalGoal newTotalGoal = totalGoal.toBuilder()
                    .aiAnalysis(goalRegistMonthlyFeedbackResponseDto.getMessage())
                    .build();

            totalGoalRepository.save(newTotalGoal);
        }
    }
}
