package com.ssafy.namani.domain.ageSalary.repository;

import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgeSalaryRepository extends JpaRepository<AgeSalary, Integer> {

    /**
     * 나이, 소득 구간으로 정보를 가져오는 메서드
     *
     * @param age
     * @param salary
     * @return AgeSalary
     */
    @Query(value = "SELECT * FROM age_salary a WHERE a.age = ?1 and a.salary = ?2", nativeQuery = true)
    Optional<AgeSalary> findByAgeSalary(Integer age, Integer salary);

}
