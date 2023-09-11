package com.ssafy.namani.domain.category.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
	@Id
	private Integer id; // 카테고리 아이디

	@NotNull
	private String name; // 카테고리 이름
}
