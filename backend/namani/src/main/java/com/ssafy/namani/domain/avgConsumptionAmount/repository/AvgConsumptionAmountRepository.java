package com.ssafy.namani.domain.avgConsumptionAmount.repository;

import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import com.ssafy.namani.domain.avgConsumptionAmount.entity.IAvgConsumptionAmountAvg;
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
     * @param curDate
     * @return Optional<AvgConsumptionAmount>
     */
    @Query(value = "SELECT * FROM avg_consumption_amount a, category c " +
            "WHERE a.category_id = c.id AND a.age_salary_id = ?1 AND DATE_FORMAT(a.reg_date, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m')", nativeQuery = true)
    Optional<List<AvgConsumptionAmount>> findAllByAgeSalaryIdRegDate(Integer ageSalaryId, Timestamp curDate);

    /**
     * 나이-소득 구간 아이디, 카테고리 아이디, 특정 연월에 해당하는 정보를 가져오는 메서드
     *
     * @param ageSalaryId
     * @param categoryId
     * @param curDate
     * @return Optional<AvgConsumptionAmount>
     */
    @Query(value = "SELECT * FROM avg_consumption_amount a" +
            " WHERE a.age_salary_id = ?1 AND a.category_id = ?2" +
            " AND DATE_FORMAT(a.reg_date, '%Y-%m') = DATE_FORMAT(?3, '%Y-%m')", nativeQuery = true)
    Optional<AvgConsumptionAmount> findByAgeSalaryIdCategoryId(Integer ageSalaryId, Integer categoryId, Timestamp curDate);

    /**
     * 연령대 + 연봉대 + 카테고리 + 특정 연월로 이전 3달간의 평균 소비 정보를 가져오는 메서드
     *
     * @param peopleNum
     * @param ageSalaryId
     * @param curDate
     * @return Optional<List<IAvgConsumptionAmountAvg>>
     */
    @Query(value = "SELECT a.category_id AS categoryId, c.name AS categoryName, SUM(a.sum_amount) / 30000 / ?1 AS amount " +
            "FROM avg_consumption_amount a, category c " +
            "WHERE a.category_id = c.id " +
            "AND a.age_salary_id = ?2 AND DATE_FORMAT(a.reg_date, '%Y-%m') BETWEEN DATE_FORMAT(DATE_SUB(?3, INTERVAL 3 MONTH), '%Y-%m') AND DATE_FORMAT(DATE_SUB(?3, INTERVAL 1 MONTH), '%Y-%m') " +
            "GROUP BY a.category_id", nativeQuery = true)
    Optional<List<IAvgConsumptionAmountAvg>> findByAgeSalaryIdRegDateForThreeMonth(Integer peopleNum, Integer ageSalaryId, Timestamp curDate);
}
