package com.ssafy.namani.domain.avgConsumptionAmount.repository;

import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvgConsumptionAmountRepository extends JpaRepository<AvgConsumptionAmount, Long> {

    /**
     * 나이, 소득 별 평균 소비값을 가져오는 메서드
     *
     * @param ageSalaryId
     * @param regDate
     * @return Optional<AvgConsumptionAmount>
     */
    @Query(value = "SELECT * FROM avg_consumption_amount a, category c " +
            "WHERE a.category_id = c.id AND a.age_salary_id = ?1 AND DATE_FORMAT(a.reg_date, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m')", nativeQuery = true)
    Optional<List<AvgConsumptionAmount>> getAvgConsumptionAmountInfo(Integer ageSalaryId, Timestamp regDate);
}
