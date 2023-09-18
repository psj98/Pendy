package com.ssafy.namani.domain.category.service;

import com.ssafy.namani.domain.category.dto.CategoryListResponseDto;
import com.ssafy.namani.global.response.BaseException;

import java.util.List;

public interface CategoryService {

    List<CategoryListResponseDto> getCategoryList() throws BaseException; // 카테고리 목록 가져오기
}
