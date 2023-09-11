package com.ssafy.namani.domain.avgConsumptionAmount.repository;

import com.ssafy.namani.domain.avgConsumptionAmount.dto.AvgConsumptionAmountDetailResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvgConsumptionAmountRepository extends JpaRepository<AvgConsumptionAmount, Long> {

    @Query(value = "SELECT * FROM avg_consumption_amount a WHERE a.age = ?1 and a.salary = ?2", nativeQuery = true)
    AvgConsumptionAmountDetailResponseDto getAvgConsumptionAmountInfo(Integer age, Integer salary);
}
