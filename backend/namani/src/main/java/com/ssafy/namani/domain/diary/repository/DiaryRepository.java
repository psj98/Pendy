package com.ssafy.namani.domain.diary.repository;

import com.ssafy.namani.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    /**
     * 일기 내용을 수정하는 메서드
     * 
     * @param id
     * @param content
     */
    @Modifying
    @Query(value = "UPDATE Diary d SET d.content = ?2 WHERE d.id = ?1")
    void updateDiary(Long id, String content);
}
