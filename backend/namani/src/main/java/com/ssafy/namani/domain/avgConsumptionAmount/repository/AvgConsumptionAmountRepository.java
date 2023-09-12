package com.ssafy.namani.domain.avgConsumptionAmount.repository;

import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface AvgConsumptionAmountRepository extends JpaRepository<AvgConsumptionAmount, Long> {

    /**
     * 나이, 소득 별 평균 소비값을 가져오는 메서드
     *
     * @param age
     * @param salary
     * @param regDate
     * @return Optional<AvgConsumptionAmount>
     */
    @Query(value = "SELECT * FROM avg_consumption_amount a WHERE a.age = ?1 and a.salary = ?2 and DATE_FORMAT(a.reg_date, '%Y-%m') = DATE_FORMAT(?3, '%Y-%m')", nativeQuery = true)
    Optional<AvgConsumptionAmount> getAvgConsumptionAmountInfo(Integer age, Integer salary, Timestamp regDate);
}
