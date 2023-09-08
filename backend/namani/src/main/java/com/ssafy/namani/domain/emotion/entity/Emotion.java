package com.ssafy.namani.domain.emotion.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Emotion {
	@Id
	private int score;
	private String name;
}
