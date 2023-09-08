package com.ssafy.namani.domain.avgConsumptionAmount.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	private Category category;

	private Integer age; // 연령대
	private Integer salary; // 연봉대
	private Integer peopleNum; // 인원수
	private Integer sumAmount; // 총합
	private Integer avgAmount; // 평균 소비 금액

	private Timestamp regDate; // 연월

}
