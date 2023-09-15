package com.ssafy.namani.domain.statistic.repository;

import com.ssafy.namani.domain.statistic.entity.DailyStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyStatisticRepository extends JpaRepository<DailyStatistic, Long> {
}
