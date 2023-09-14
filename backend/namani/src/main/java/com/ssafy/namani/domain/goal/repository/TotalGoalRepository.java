package com.ssafy.namani.domain.goal.repository;

import com.ssafy.namani.domain.goal.entity.TotalGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TotalGoalRepository extends JpaRepository<TotalGoal, Long> {

    /**
     * 로그인한 사용자 및 현재 연월에 해당하는 목표 정보가 있는지 체크하는 메서드
     *
     * @param memberId
     * @param curDate
     * @return Optional<TotalGoal>
     */
    @Query(value = "SELECT * FROM total_goal t " +
            "WHERE t.member_id = ?1 AND DATE_FORMAT(t.goal_date, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m')", nativeQuery = true)
    Optional<TotalGoal> findByCurDate(UUID memberId, Timestamp curDate);
}
