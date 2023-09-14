package com.ssafy.namani.domain.goal.repository;

import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalByCategoryRepository extends JpaRepository<GoalByCategory, Long> {
}
