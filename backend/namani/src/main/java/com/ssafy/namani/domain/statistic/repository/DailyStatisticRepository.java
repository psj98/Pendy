package com.ssafy.namani.domain.statistic.repository;

import com.ssafy.namani.domain.statistic.entity.DailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DailyStatisticRepository extends JpaRepository<DailyStatistic, Long> {

    /**
     * 사용자 + 특정 일로 모든 일간 통계 정보를 가져오는 메서드
     *
     * @param memberId
     * @param curDate
     * @return Optional<DailyStatistic>
     */
    @Query(value = "SELECT * FROM daily_statistic d " +
            "WHERE d.member_id = ?1 AND DATE_FORMAT(d.reg_date, '%Y-%m-%d') = DATE_FORMAT(?2, '%Y-%m-%d')", nativeQuery = true)
    Optional<List<DailyStatistic>> findAllByMemberIdRegDate(UUID memberId, Timestamp curDate);

    /**
     * 사용자 + 카테고리 + 특정 연월로 일간 통계 정보를 가져오는 메서드
     *
     * @param memberId
     * @param categoryId
     * @param curDate
     * @return Optional<DailyStatistic>
     */
    @Query(value = "SELECT * FROM daily_statistic d " +
            "WHERE d.member_id = ?1 AND d.category_id = ?2 AND DATE_FORMAT(d.reg_date, '%Y-%m') = DATE_FORMAT(?3, '%Y-%m')", nativeQuery = true)
    Optional<DailyStatistic> findByMemberIdCategoryIdRegDate(UUID memberId, Integer categoryId, Timestamp curDate);
}
