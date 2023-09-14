package com.ssafy.namani.domain.statistic.service;

import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.domain.member.entity.Member;
import com.ssafy.namani.domain.member.repository.MemberRepository;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticDetailByRegDateResponseDto;
import com.ssafy.namani.domain.statistic.entity.MonthlyStatistic;
import com.ssafy.namani.domain.statistic.repository.DailyStatisticRepository;
import com.ssafy.namani.domain.statistic.repository.MonthlyStatisticRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final DailyStatisticRepository dailyStatisticRepository;
    private final MonthlyStatisticRepository monthlyStatisticRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

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
        List<Category> categoryList = categoryRepository.findAll();

        // 사용자 정보 체크
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_MEMBER);
        }

        // 월간 통계 정보 체크
        Optional<List<MonthlyStatistic>> monthlyStatisticListOptional = monthlyStatisticRepository.findAllByMemberIdRegDate(memberId, curDate);
        if (!monthlyStatisticListOptional.isPresent()) {
            for (Category category : categoryList) {
                MonthlyStatistic monthlyStatistic = MonthlyStatistic.builder()
                        .member(memberOptional.get())
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
                            .member(memberOptional.get())
                            .category(category)
                            .amount(0)
                            .regDate(curDate)
                            .build();

                    monthlyStatisticRepository.save(monthlyStatistic);
                }
            }
        }

        // 수정된 정보가 있을 수도 있기 때문에 다시 조회
        monthlyStatisticListOptional = monthlyStatisticRepository.findAllByMemberIdRegDate(memberId, curDate);

        // 월간 통계 정보 저장
        List<MonthlyStatistic> monthlyStatisticList = monthlyStatisticListOptional.get();
        List<MonthlyStatisticAmountByCategoryResponseDto> amountByCategory = new ArrayList<>();
        Integer totalAmount = 0;

        for (MonthlyStatistic monthlyStatistic : monthlyStatisticList) {
            MonthlyStatisticAmountByCategoryResponseDto newAmountByCategory
                    = MonthlyStatisticAmountByCategoryResponseDto.builder()
                    .categoryId(monthlyStatistic.getCategory().getId())
                    .categoryName(monthlyStatistic.getCategory().getName())
                    .amount(monthlyStatistic.getAmount())
                    .build();

            amountByCategory.add(newAmountByCategory);

            totalAmount += monthlyStatistic.getAmount();
        }

        MonthlyStatisticDetailByRegDateResponseDto monthlyStatistic
                = MonthlyStatisticDetailByRegDateResponseDto.builder()
                .amountByCategory(amountByCategory)
                .totalAmount(totalAmount)
                .build();

        return monthlyStatistic;
    }
}
