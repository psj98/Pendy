package com.ssafy.namani.domain.category.controller;

import com.ssafy.namani.domain.category.dto.CategoryListResponseDto;
import com.ssafy.namani.domain.category.service.CategoryServiceImpl;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    private final BaseResponseService baseResponseService;

    /**
     * 카테고리 목록을 가져오는 API
     *
     * @return categoryListResponseDtoList
     */
    @GetMapping("/list")
    public BaseResponse<Object> getCategoryList() {
        try {
            List<CategoryListResponseDto> categoryListResponseDtoList = categoryService.getCategoryList();
            return baseResponseService.getSuccessResponse(categoryListResponseDtoList);
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }
}
