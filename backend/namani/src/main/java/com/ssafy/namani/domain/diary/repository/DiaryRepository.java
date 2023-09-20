package com.ssafy.namani.domain.diary.repository;

import com.ssafy.namani.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    /**
     * 사용자와 현재 월로 일기 목록을 조회하는 메서드
     *
     * @param memberId
     * @param curDate
     * @return Optional<List<Diary>>
     */
    @Query(value = "SELECT * FROM diary d " +
            "WHERE d.member_id = ?1 AND DATE_FORMAT(d.reg_date, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m')", nativeQuery = true)
    Optional<List<Diary>> findAllByMemberIdTodayMonth(UUID memberId, Timestamp curDate);

    /**
     * 사용자와 현재 일로 일기 정보를 조회하는 메서드
     * 
     * @param memberId
     * @param curDate
     * @return Optional<Diary>
     */
    @Query(value = "SELECT * FROM diary d " +
            "WHERE d.member_id = ?1 AND DATE_FORMAT(d.reg_date, '%Y-%m-%d') = DATE_FORMAT(?2, '%Y-%m-%d')", nativeQuery = true)
    Optional<Diary> findByMemberIdTodayDate(UUID memberId, Timestamp curDate);
}
