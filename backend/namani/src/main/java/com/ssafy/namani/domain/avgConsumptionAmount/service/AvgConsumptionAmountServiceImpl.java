package com.ssafy.namani.domain.avgConsumptionAmount.service;

import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import com.ssafy.namani.domain.avgConsumptionAmount.repository.AvgConsumptionAmountRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvgConsumptionAmountServiceImpl implements AvgConsumptionAmountService {

    private final AvgConsumptionAmountRepository avgConsumptionAmountRepository;

    /**
     * 나이, 소득 별 평균 소비값을 가져오는 메서드
     *
     * @param age
     * @param salary
     * @param regDate
     * @return
     * @throws AvgConsumptionAmount
     */
    public AvgConsumptionAmount getAvgConsumptionAmountInfo(Integer age, Integer salary, Timestamp regDate) throws BaseException {
        Optional<AvgConsumptionAmount> avgConsumptionAmount = avgConsumptionAmountRepository.getAvgConsumptionAmountInfo(age, salary, regDate);

        // 나이, 소득 구간에 해당하는 평균 소비값 유무 체크
        if (avgConsumptionAmount.isPresent()) {
            return avgConsumptionAmount.get();
        } else {
            throw new BaseException(BaseResponseStatus.NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY);
        }
    }
}
