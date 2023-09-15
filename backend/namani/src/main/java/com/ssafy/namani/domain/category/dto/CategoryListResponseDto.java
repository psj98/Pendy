package com.ssafy.namani.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListResponseDto {

    @NotNull
    private Integer id; // 카테고리 아이디

    @NotNull
    private String name; // 카테고리 이름
}
