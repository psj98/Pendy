package com.ssafy.namani.domain.avgConsumptionAmount.entity;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.category.entity.Category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp regDate; // 연월

	@Builder
	public AvgConsumptionAmount(Long id, AgeSalary ageSalary, Category category, Integer sumAmount, Timestamp regDate) {
		this.id = id;
		this.ageSalary = ageSalary;
		this.category = category;
		this.sumAmount = sumAmount;
		this.regDate = regDate;
	}
}
