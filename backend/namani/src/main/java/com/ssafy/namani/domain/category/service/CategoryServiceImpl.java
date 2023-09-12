package com.ssafy.namani.domain.category.service;

import com.ssafy.namani.domain.category.dto.CategoryListResponseDto;
import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.global.response.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 목록을 가져오는 API
     *
     * @return categoryListResponseDtoList
     * @throws BaseException
     */
    @Override
    public List<CategoryListResponseDto> getCategoryList() throws BaseException {
        List<CategoryListResponseDto> categoryListResponseDtoList = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll(); // 카테고리 목록 조회

        for (Category category : categoryList) {
            Integer id = category.getId(); // 카테고리 아이디
            String name = category.getName(); // 카테고리 이름

            // 카테고리 목록에 저장
            CategoryListResponseDto categoryListResponseDto = new CategoryListResponseDto(id, name);
            categoryListResponseDtoList.add(categoryListResponseDto);
        }

        return categoryListResponseDtoList;
    }
}
