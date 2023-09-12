package com.ssafy.namani.domain.category.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
	
	@Id
	private Integer id; // 카테고리 아이디

	@NotNull
	private String name; // 카테고리 이름

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
