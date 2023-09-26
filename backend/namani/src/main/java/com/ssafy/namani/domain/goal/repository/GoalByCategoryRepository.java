package com.ssafy.namani.domain.goal.repository;

import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalByCategoryRepository extends JpaRepository<GoalByCategory, Long> {

    /**
     * 월별 목표 id와 카테고리 id로 카테고리 별 목표 조회
     *
     * @param totalGoalId
     * @param categoryId
     * @return Optional<GoalByCategory>
     */
    @Query(value = "SELECT * FROM goal_by_category g " +
            "WHERE g.total_goal_id = ?1 AND g.category_id = ?2", nativeQuery = true)
    Optional<GoalByCategory> findByTotalGoalIdCategoryId(Long totalGoalId, Integer categoryId);

    /**
     * 월별 목표 id로 카테고리 별 목표 리스트 조회
     *
     * @param totalGoalId
     * @return Optional<List<GoalByCategory>>
     */
    @Query(value = "SELECT * FROM goal_by_category g " +
            "WHERE g.total_goal_id = ?1", nativeQuery = true)
    Optional<List<GoalByCategory>> findAllByTotalGoalId(Long totalGoalId);

    void deleteByTotalGoalId(Long totalGoalId);
}
