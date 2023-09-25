package com.ssafy.namani.domain.avgConsumptionAmount.service;

import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.ageSalary.repository.AgeSalaryRepository;
import com.ssafy.namani.domain.avgConsumptionAmount.dto.response.AvgConsumptionAmountDetailResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.dto.response.AvgConsumptionAmountForThreeMonthResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import com.ssafy.namani.domain.avgConsumptionAmount.entity.IAvgConsumptionAmountAvg;
import com.ssafy.namani.domain.avgConsumptionAmount.repository.AvgConsumptionAmountRepository;
import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.domain.statistic.dto.response.MonthlyStatisticAmountByCategoryResponseDto;
import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;
import com.ssafy.namani.domain.transactionInfo.repository.TransactionInfoRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class AvgConsumptionAmountServiceImpl implements AvgConsumptionAmountService {

    private final AvgConsumptionAmountRepository avgConsumptionAmountRepository;
    private final AgeSalaryRepository ageSalaryRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionInfoRepository transactionInfoRepository;

    /**
     * 나이, 소득 별 평균 소비값을 가져오는 메서드
     *
     * @param age
     * @param salary
     * @param curDate
     * @return List<AvgConsumptionAmountDetailDto>
     * @throws BaseException
     */
    @Override
    public List<AvgConsumptionAmountDetailResponseDto> getAvgConsumptionAmountList(Integer age, Integer salary, Timestamp curDate) throws BaseException {
        /* 나이-소득 정보 조회 */
        Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age, salary);

        // 나이-소득 구간 정보가 존재하는지 체크
        if (!ageSalaryOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
        }

        AgeSalary ageSalary = ageSalaryOptional.get(); // 나이-소득 정보
        Integer ageSalaryId = ageSalary.getId(); // 나이-소득 아이디
        Integer peopleNum = ageSalary.getPeopleNum(); // 인원수

        /* 나이-소득, 해당 연월로 평균 소비 정보 조회 */
        Optional<List<AvgConsumptionAmount>> avgConsumptionAmountOptional = avgConsumptionAmountRepository.findAllByAgeSalaryIdRegDate(ageSalaryId, curDate);

        // 평균 소비 정보가 존재하는지 체크
        if (!avgConsumptionAmountOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY_ID_AND_REG_DATE);
        }

        List<AvgConsumptionAmount> avgConsumptionAmount = avgConsumptionAmountOptional.get(); // 평균 소비 정보
        List<AvgConsumptionAmountDetailResponseDto> avgConsumptionAmountDetailResponseDtoList = new ArrayList<>(); // return 값

        // 반복문을 통해 평균 소비값을 계산하여 카테고리 정보와 함께 저장
        for (AvgConsumptionAmount amount : avgConsumptionAmount) {
            Integer sumAmount = amount.getSumAmount();
            Double avgAmount = (double) (sumAmount / peopleNum);

            AvgConsumptionAmountDetailResponseDto avgConsumptionAmountDetailResponseDto
                    = AvgConsumptionAmountDetailResponseDto.builder()
                    .categoryId(amount.getCategory().getId())
                    .categoryName(amount.getCategory().getName())
                    .avgAmount(avgAmount)
                    .build();

            avgConsumptionAmountDetailResponseDtoList.add(avgConsumptionAmountDetailResponseDto);
        }

        return avgConsumptionAmountDetailResponseDtoList;
    }

    /**
     * 매달 1일 모든 카테고리 + 모든 나이-소득 에 해당하는 avgConsumptionAmount 값을 생성하는 메서드
     */
    @Override
    @Scheduled(cron = "0 0 0 1 * *")
    public void registAvgConsumptionAmount() {
        List<Category> categoryList = categoryRepository.findAll();
        List<AgeSalary> ageSalaryList = ageSalaryRepository.findAll();

        for (AgeSalary ageSalary : ageSalaryList) {
            for (Category category : categoryList) {
                AvgConsumptionAmount avgConsumptionAmount
                        = AvgConsumptionAmount.builder()
                        .ageSalary(ageSalary)
                        .category(category)
                        .sumAmount(0)
                        .build();

                avgConsumptionAmountRepository.save(avgConsumptionAmount);
            }
        }
    }

    /**
     * 회원가입 시, 평균 소비 금액을 업데이트하는 메서드
     *
     * @param accountNumber
     * @param age
     * @param salary
     * @throws BaseException
     */
    @Override
    public void updateAvgConsumptionAmountByMemberJoin(String accountNumber, Integer age, Integer salary) throws BaseException {
        // 계좌 번호로 현재까지의 거래 내역 조회
        Optional<List<TransactionInfo>> transactionInfoOptional = transactionInfoRepository.findAllByAccountNumber(accountNumber);

        // 거래 내역 정보가 없는 경우, 정보 업데이트 X
        if (!transactionInfoOptional.isPresent()) {
            return;
        }

        // 연령대, 연봉대로 AgeSalary 정보 가져오기
        Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age, salary);

        // AgeSalary 정보가 없는 경우
        if (!ageSalaryOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
        }

        // 모든 거래 내역에 대해 평균 소비 정보 업데이트 실행
        List<TransactionInfo> transactionInfoList = transactionInfoOptional.get();
        AgeSalary ageSalary = ageSalaryOptional.get();
        for (TransactionInfo transactionInfo : transactionInfoList) {
            // 입금인 경우, 스킵
            if (transactionInfo.getTransactionType() == 1) {
                continue;
            }

            // 카테고리 조회
            Category category = transactionInfo.getCategory();

            // AvgConsumptionAmount 정보 가져오기
            Optional<AvgConsumptionAmount> avgConsumptionAmountOptional = avgConsumptionAmountRepository.findByAgeSalaryIdCategoryId(ageSalary.getId(), category.getId(), transactionInfo.getTradeDate());

            // 평균 소비 정보가 없는 경우
            if (!avgConsumptionAmountOptional.isPresent()) {
                throw new BaseException(BaseResponseStatus.NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY_ID_AND_CATEGORY_ID_AND_REG_DATE);
            }

            // 카테고리와 AgeSalary에 맞게 sumAmount 업데이트
            AvgConsumptionAmount avgConsumptionAmount = avgConsumptionAmountOptional.get();
            AvgConsumptionAmount newAvgConsumptionAmount = AvgConsumptionAmount.builder()
                    .id(avgConsumptionAmount.getId())
                    .ageSalary(ageSalary)
                    .category(category)
                    .sumAmount(avgConsumptionAmount.getSumAmount() + transactionInfo.getTransactionAmount())
                    .regDate(avgConsumptionAmount.getRegDate())
                    .build();

            avgConsumptionAmountRepository.save(newAvgConsumptionAmount);
        }
    }

    /**
     * 회원 연령대 + 연봉대에 해당ㅎ하는 3달간 평균 소비 통계 가져오기
     *
     * @param age
     * @param salary
     * @param curDate
     * @return
     * @throws BaseException
     */
    @Override
    public List<AvgConsumptionAmountForThreeMonthResponseDto> getAvgConsumptionAmountForThreeMonth(Integer age, Integer salary, Timestamp curDate) throws BaseException {
        List<AvgConsumptionAmountForThreeMonthResponseDto> avgConsumptionAmountAvgList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();
        for(Category category : categoryList){
            avgConsumptionAmountAvgList.add(new AvgConsumptionAmountForThreeMonthResponseDto(category.getId(), category.getName(), 0));
        }

        // 연령대 + 연봉대 체크
        Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age / 10 * 10, salary / 10000000 * 1000);
        if (!ageSalaryOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
        }


        // 평균 소비 정보 체크
        AgeSalary ageSalary = ageSalaryOptional.get();
        List<IAvgConsumptionAmountAvg> iAvgConsumptionAmountAvgList = avgConsumptionAmountRepository.findByAgeSalaryIdRegDateForThreeMonth(ageSalary.getPeopleNum(), ageSalary.getId(), curDate).get();
        for (IAvgConsumptionAmountAvg iAvgConsumptionAmountAvg : iAvgConsumptionAmountAvgList) {
            AvgConsumptionAmountForThreeMonthResponseDto curAmountByCategory = avgConsumptionAmountAvgList.get(iAvgConsumptionAmountAvg.getCategoryId() - 1);
            AvgConsumptionAmountForThreeMonthResponseDto newAmountByCategory = curAmountByCategory.toBuilder()
                    .amount(iAvgConsumptionAmountAvg.getAmount())
                    .build();

            avgConsumptionAmountAvgList.set(iAvgConsumptionAmountAvg.getCategoryId() - 1, newAmountByCategory);
        }

        log.info("1"+ avgConsumptionAmountAvgList.toString());

        return avgConsumptionAmountAvgList;
    }
}
