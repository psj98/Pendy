package com.ssafy.namani.domain.emotion.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Entity
@Getter
public class Emotion {

    @Id
    private Integer emotionScore; // 감정 점수

    @NotNull
    private String emotionName; // 감정명
}
