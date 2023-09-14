package com.ssafy.namani.domain.statistic.repository;

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

    @Query(value = "SELECT * FROM monthly_statistic m " +
            "WHERE m.member_id = ?1 AND DATE_FORMAT(m.reg_date, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m')", nativeQuery = true)
    Optional<List<MonthlyStatistic>> findAllByMemberIdRegDate(UUID memberId, Timestamp curDate);

    @Query(value = "SELECT * FROM monthly_statistic m " +
            "WHERE m.member_id = ?1 AND m.category_id = ?2 AND DATE_FORMAT(m.reg_date, '%Y-%m') = DATE_FORMAT(?3, '%Y-%m')", nativeQuery = true)
    Optional<MonthlyStatistic> findByMemberIdCategoryIdRegDate(UUID memberId, Integer categoryId, Timestamp curDate);
}
