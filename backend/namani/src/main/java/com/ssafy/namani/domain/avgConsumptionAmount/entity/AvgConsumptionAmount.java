package com.ssafy.namani.domain.avgConsumptionAmount.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.ssafy.namani.domain.category.entity.Category;

import lombok.Getter;

@Entity
@Getter
public class AvgConsumptionAmount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 평균 소비 금액 아이디

	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category; // 카테고리 정보

	@NotNull
	private Integer age; // 연령대

	@NotNull
	private Integer salary; // 연봉대

	@NotNull
	private Integer peopleNum; // 인원수

	@NotNull
	private Integer sumAmount; // 총합

	@NotNull
	private Integer avgAmount; // 평균 소비 금액

	@NotNull
	private Timestamp regDate; // 연월
}
