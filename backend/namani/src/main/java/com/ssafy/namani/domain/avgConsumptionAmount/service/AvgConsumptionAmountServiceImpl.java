package com.ssafy.namani.domain.avgConsumptionAmount.service;

import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.ageSalary.repository.AgeSalaryRepository;
import com.ssafy.namani.domain.avgConsumptionAmount.dto.AvgConsumptionAmountDetailResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import com.ssafy.namani.domain.avgConsumptionAmount.repository.AvgConsumptionAmountRepository;
import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
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
public class AvgConsumptionAmountServiceImpl implements AvgConsumptionAmountService {

    private final AvgConsumptionAmountRepository avgConsumptionAmountRepository;
    private final AgeSalaryRepository ageSalaryRepository;
    private final CategoryRepository categoryRepository;

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
}
