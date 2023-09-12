package com.ssafy.namani.domain.avgConsumptionAmount.entity;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.category.entity.Category;

import lombok.Getter;

@Entity
@Getter
public class AvgConsumptionAmount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 평균 소비 금액 아이디

	@OneToOne
	@JoinColumn(name = "age_salary_id")
	private AgeSalary ageSalary; // 나이, 소득 정보

	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category; // 카테고리 정보

	@NotNull
	private Integer sumAmount; // 총합

	@NotNull
	private Timestamp regDate; // 연월
}
