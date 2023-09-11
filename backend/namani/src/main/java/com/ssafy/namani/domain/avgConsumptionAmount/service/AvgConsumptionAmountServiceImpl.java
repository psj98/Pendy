package com.ssafy.namani.domain.avgConsumptionAmount.service;

import com.ssafy.namani.domain.avgConsumptionAmount.dto.AvgConsumptionAmountDetailResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.repository.AvgConsumptionAmountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvgConsumptionAmountServiceImpl {

    private final AvgConsumptionAmountRepository avgConsumptionAmountRepository;

    /**
     * 나이, 소득 별 평균 소비값을 가져오는 메서드
     *
     * @param age
     * @param salary
     * @return avgConsumptionAmountDetailResponseDto
     */
    public AvgConsumptionAmountDetailResponseDto getAvgConsumptionAmountInfo(Integer age, Integer salary) {
        AvgConsumptionAmountDetailResponseDto avgConsumptionAmountDetailResponseDto = avgConsumptionAmountRepository.getAvgConsumptionAmountInfo(age, salary);
        
        if(avgConsumptionAmountDetailResponseDto == null) {
            // error 체크
        }
        
        return avgConsumptionAmountDetailResponseDto;
    }
}
