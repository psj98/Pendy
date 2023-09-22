package com.ssafy.namani.domain.statistic.repository;

import com.ssafy.namani.domain.statistic.entity.IMonthlyStatisticAvg;
import com.ssafy.namani.domain.statistic.entity.MonthlyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonthlyStatisticRepository extends JpaRepository<MonthlyStatistic, Long> {

    /**
     * 사용자 + 특정 연월로 모든 월간 통계 정보를 가져오는 메서드
     *
     * @param memberId
     * @param curDate
     * @return Optional<List < MonthlyStatistic>>
     */
    @Query(value = "SELECT * FROM monthly_statistic m " +
            "WHERE m.member_id = ?1 AND DATE_FORMAT(m.reg_date, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m')", nativeQuery = true)
    Optional<List<MonthlyStatistic>> findAllByMemberIdRegDate(UUID memberId, Timestamp curDate);

    /**
     * 사용자 + 카테고리 + 특정 연월로 월간 통계 정보를 가져오는 메서드
     *
     * @param memberId
     * @param categoryId
     * @param curDate
     * @return Optional<MonthlyStatistic>
     */
    @Query(value = "SELECT * FROM monthly_statistic m " +
            "WHERE m.member_id = ?1 AND m.category_id = ?2 AND DATE_FORMAT(m.reg_date, '%Y-%m') = DATE_FORMAT(?3, '%Y-%m')", nativeQuery = true)
    Optional<MonthlyStatistic> findByMemberIdCategoryIdRegDate(UUID memberId, Integer categoryId, Timestamp curDate);

    /**
     * 사용자 + 카테고리 + 특정 연월로 이전 3달간의 통계 정보를 가져오는 메서드
     *
     * @param memberId
     * @param curDate
     * @return Optional<List < IMonthlyStatisticSum>>
     */
    @Query(value = "SELECT m.category_id AS categoryId, c.name AS categoryName, SUM(m.amount) / 30000 AS amount FROM monthly_statistic m, category c " +
            "WHERE m.category_id = c.id " +
            "AND m.member_id = ?1 " +
            "AND DATE_FORMAT(m.reg_date, '%Y-%m') BETWEEN DATE_FORMAT(DATE_SUB(?2, INTERVAL 3 MONTH), '%Y-%m') AND DATE_FORMAT(DATE_SUB(?2, INTERVAL 1 MONTH), '%Y-%m') " +
            "GROUP BY m.category_id", nativeQuery = true)
    Optional<List<IMonthlyStatisticAvg>> findByMemberIdRegDateForThreeMonth(UUID memberId, Timestamp curDate);
}
