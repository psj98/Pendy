package com.ssafy.namani.domain.emotion.repository;

import com.ssafy.namani.domain.emotion.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Integer> {
}
