package com.ssafy.namani.domain.statistic.service;

import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.domain.member.entity.Member;
import com.ssafy.namani.domain.member.repository.MemberRepository;
import com.ssafy.namani.domain.statistic.dto.response.DailyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.DailyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.domain.statistic.entity.DailyStatistic;
import com.ssafy.namani.domain.statistic.entity.IMonthlyStatisticAvg;
import com.ssafy.namani.domain.statistic.entity.MonthlyStatistic;
import com.ssafy.namani.domain.statistic.repository.DailyStatisticRepository;
import com.ssafy.namani.domain.statistic.repository.MonthlyStatisticRepository;
import com.ssafy.namani.domain.transactionInfo.entity.ITransactionInfoList;
import com.ssafy.namani.domain.transactionInfo.repository.TransactionInfoRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final DailyStatisticRepository dailyStatisticRepository;
    private final MonthlyStatisticRepository monthlyStatisticRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionInfoRepository transactionInfoRepository;

    @Override
    public void checkDailyStatistic(UUID memberId, Timestamp curDate) {
        List<Category> categoryList = categoryRepository.findAll();
        Member member = memberRepository.findById(memberId).get();

        // 월간 통계 정보 체크
        Optional<List<DailyStatistic>> dailyStatisticListOptional = dailyStatisticRepository.findAllByMemberIdRegDate(memberId, curDate);
        if (!dailyStatisticListOptional.isPresent()) {
            for (Category category : categoryList) {
                DailyStatistic dailyStatistic = DailyStatistic.builder()
                        .member(member)
                        .category(category)
                        .amount(0)
                        .regDate(curDate)
                        .build();

                dailyStatisticRepository.save(dailyStatistic);
            }
        } else {
            for (Category category : categoryList) {
                // 사용자 + 카테고리 + 연월로 통계 정보 체크
                Optional<DailyStatistic> dailyStatisticOptional = dailyStatisticRepository.findByMemberIdCategoryIdRegDate(memberId, category.getId(), curDate);
                if (!dailyStatisticOptional.isPresent()) {
                    DailyStatistic dailyStatistic = DailyStatistic.builder()
                            .member(member)
                            .category(category)
                            .amount(0)
                            .regDate(curDate)
                            .build();

                    dailyStatisticRepository.save(dailyStatistic);
                }
            }
        }
    }

    /**
     * 월간 통계 존재 여부 체크
     *
     * @param memberId
     * @param curDate
     */
    @Override
    public void checkMonthlyStatistic(UUID memberId, Timestamp curDate) {
        List<Category> categoryList = categoryRepository.findAll();
        Member member = memberRepository.findById(memberId).get();

        // 월간 통계 정보 체크
        Optional<List<MonthlyStatistic>> monthlyStatisticListOptional = monthlyStatisticRepository.findAllByMemberIdRegDate(memberId, curDate);
        if (!monthlyStatisticListOptional.isPresent()) {
            for (Category category : categoryList) {
                MonthlyStatistic monthlyStatistic = MonthlyStatistic.builder()
                        .member(member)
                        .category(category)
                        .amount(0)
                        .regDate(curDate)
                        .build();

                monthlyStatisticRepository.save(monthlyStatistic);
            }
        } else {
            for (Category category : categoryList) {
                // 사용자 + 카테고리 + 연월로 통계 정보 체크
                Optional<MonthlyStatistic> monthlyStatisticOptional = monthlyStatisticRepository.findByMemberIdCategoryIdRegDate(memberId, category.getId(), curDate);
                if (!monthlyStatisticOptional.isPresent()) {
                    MonthlyStatistic monthlyStatistic = MonthlyStatistic.builder()
                            .member(member)
                            .category(category)
                            .amount(0)
                            .regDate(curDate)
                            .build();

                    monthlyStatisticRepository.save(monthlyStatistic);
                }
            }
        }
    }

    /**
     * 로그인 한 사용자의 아이디 + 특정 날짜에 해당하는 일간 통계 정보 조회
     *
     * @param memberId
     * @param curDate
     * @return DailyStatisticDetailByRegDateResponseDto
     * @throws BaseException
     */
    @Override
    public DailyStatisticDetailByRegDateResponseDto getDailyStatisticByRegDate(UUID memberId, Timestamp curDate) throws BaseException {
        // 사용자 정보 체크
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_MEMBER);
        }

        checkDailyStatistic(memberId, curDate);

        // 카테고리 정보로 List 초기화
        List<Category> categoryList = categoryRepository.findAll();
        Integer totalAmount = 0;
        List<DailyStatisticAmountByCategoryResponseDto> amountByCategory = new ArrayList<>();
        for (Category category : categoryList) {
            amountByCategory.add(new DailyStatisticAmountByCategoryResponseDto(category.getId(), category.getName(), 0));
        }

        // 사용자의 모든 계좌에서 현재 일에 해당하는 거래 내역을 카테고리 별로 조회 => 총합으로 구함
        List<ITransactionInfoList> transactionInfoList = transactionInfoRepository.findDailyStatisticByMemberIdAccountNumberRegDate(memberId, curDate).get();
        for (ITransactionInfoList transactionInfo : transactionInfoList) {
            DailyStatisticAmountByCategoryResponseDto curAmountByCategory = amountByCategory.get(transactionInfo.getCategoryId() - 1);
            DailyStatisticAmountByCategoryResponseDto newAmountByCategory = curAmountByCategory.toBuilder()
                    .amount(transactionInfo.getAmount())
                    .build();

            amountByCategory.set(transactionInfo.getCategoryId() - 1, newAmountByCategory);

            totalAmount += transactionInfo.getAmount();
        }

        // 일간 통계 저장
        DailyStatisticDetailByRegDateResponseDto dailyStatistic
                = DailyStatisticDetailByRegDateResponseDto.builder()
                .amountByCategory(amountByCategory)
                .totalAmount(totalAmount)
                .build();

        return dailyStatistic;
    }

    /**
     * 로그인 한 사용자의 아이디 + 특정 날짜에 해당하는 월간 통계 정보 조회
     *
     * @param memberId
     * @param curDate
     * @return MonthlyStatisticDetailByRegDateResponseDto
     * @throws BaseException
     */
    @Override
    public MonthlyStatisticDetailByRegDateResponseDto getMonthlyStatisticByRegDate(UUID memberId, Timestamp curDate) throws BaseException {
        // 사용자 정보 체크
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_MEMBER);
        }

        checkMonthlyStatistic(memberId, curDate);

        // 카테고리 정보로 List 초기화
        Integer totalAmount = 0;
        List<MonthlyStatisticAmountByCategoryResponseDto> amountByCategory = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        for (Category category : categoryList) {
            amountByCategory.add(new MonthlyStatisticAmountByCategoryResponseDto(category.getId(), category.getName(), 0));
        }

        // 사용자의 모든 계좌에서 현재 월에 해당하는 거래 내역을 카테고리 별로 조회 => 총합으로 구함
        List<ITransactionInfoList> transactionInfoList = transactionInfoRepository.findMonthlyStatisticByMemberIdAccountNumberRegDate(memberId, curDate).get();
        for (ITransactionInfoList transactionInfo : transactionInfoList) {
            MonthlyStatisticAmountByCategoryResponseDto curAmountByCategory = amountByCategory.get(transactionInfo.getCategoryId() - 1);
            MonthlyStatisticAmountByCategoryResponseDto newAmountByCategory = curAmountByCategory.toBuilder()
                    .amount(transactionInfo.getAmount())
                    .build();

            amountByCategory.set(transactionInfo.getCategoryId() - 1, newAmountByCategory);

            totalAmount += transactionInfo.getAmount();
        }

        // 월간 통계 저장
        MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic
                = MonthlyStatisticDetailByRegDateResponseDto.builder()
                .amountByCategory(amountByCategory)
                .totalAmount(totalAmount)
                .build();

        return monthlyStatistic;
    }

    /**
     * 사용자 + 카테고리 + 특정 연월로 이전 3달간의 통계 정보를 가져오는 메서드
     *
     * @param memberId
     * @param curDate
     * @return List<MonthlyStatisticAmountByCategoryResponseDto>
     * @throws BaseException
     */
    @Override
    public List<MonthlyStatisticAmountByCategoryResponseDto> getMonthlyStatisticAvgForThreeMonth(UUID memberId, Timestamp curDate) throws BaseException {
        // 사용자 정보 체크
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_MEMBER);
        }

        List<MonthlyStatisticAmountByCategoryResponseDto> amountByCategory = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        for (Category category : categoryList) {
            amountByCategory.add(new MonthlyStatisticAmountByCategoryResponseDto(category.getId(), category.getName(), 0));
        }

        List<ITransactionInfoList> transactionInfoList = transactionInfoRepository.findMonthlyStatisticByMemberIdAccountNumberRegDateForThreeMonth(memberId, curDate).get();
        for (ITransactionInfoList transactionInfo : transactionInfoList) {
            MonthlyStatisticAmountByCategoryResponseDto curAmountByCategory = amountByCategory.get(transactionInfo.getCategoryId() - 1);
            MonthlyStatisticAmountByCategoryResponseDto newAmountByCategory = curAmountByCategory.toBuilder()
                    .amount(transactionInfo.getAmount())
                    .build();

            amountByCategory.set(transactionInfo.getCategoryId() - 1, newAmountByCategory);
        }

        return amountByCategory;
    }
}
