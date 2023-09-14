package com.ssafy.namani.domain.goal.service;

import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.domain.goal.dto.request.GoalDetailRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalRegistRequestDto;
import com.ssafy.namani.domain.goal.dto.request.GoalUpdateRequestDto;
import com.ssafy.namani.domain.goal.dto.response.GoalByCategoryDetailResponseDto;
import com.ssafy.namani.domain.goal.dto.response.GoalCheckResponseDto;
import com.ssafy.namani.domain.goal.dto.response.GoalDetailResponseDto;
import com.ssafy.namani.domain.goal.entity.GoalByCategory;
import com.ssafy.namani.domain.goal.entity.TotalGoal;
import com.ssafy.namani.domain.goal.repository.GoalByCategoryRepository;
import com.ssafy.namani.domain.goal.repository.TotalGoalRepository;
import com.ssafy.namani.domain.member.entity.Member;
import com.ssafy.namani.domain.member.repository.MemberRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final TotalGoalRepository totalGoalRepository;
    private final GoalByCategoryRepository goalByCategoryRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 로그인한 사용자 및 현재 연월에 해당하는 목표 정보가 있는지 체크하는 메서드
     *
     * @param memberId
     * @return GoalCheckResponseDto
     * @throws BaseException
     */
    @Override
    public GoalCheckResponseDto checkGoalByCurDate(UUID memberId) throws BaseException {
        Optional<Member> memberOptional = memberRepository.findById(memberId); // 사용자 정보

        // 사용자 정보 체크
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER);
        }

        // 로그인한 사용자 및 현재 연월에 해당하는 목표 정보가 있는지 체크
        Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findByCurDate(memberId, Timestamp.valueOf(LocalDateTime.now()));

        if (totalGoalOptional.isPresent()) { // 존재하는 경우, "존재하여 생성할 수 없다" & 메인 페이지
            return GoalCheckResponseDto.builder()
                    .check(true).build();
        } else { // 존재하지 않는 경우, 생성 페이지
            return GoalCheckResponseDto.builder()
                    .check(false).build();
        }
    }

    /**
     * 월별 목표 등록 메서드
     *
     * @param memberId
     * @param goalRegistRequestDto
     * @throws BaseException
     */
    @Override
    public void registGoal(UUID memberId, GoalRegistRequestDto goalRegistRequestDto) throws BaseException {
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        // 사용자 정보 체크
        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER);
        }

        Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findByCurDate(memberId, Timestamp.valueOf(LocalDateTime.now()));

        // 목표 정보 체크
        if (totalGoalOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.TOTAL_GOAL_IS_ALREADY_PRESENT);
        }

        // 월별 목표 정보 저장
        Integer goalAmount = goalRegistRequestDto.getGoalAmount();
        List<GoalByCategoryDetailResponseDto> goalByCategoryList = goalRegistRequestDto.getGoalByCategoryList();

        TotalGoal totalGoal = TotalGoal.builder()
                .member(memberOptional.get())
                .goalAmount(goalAmount)
                .goalDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        totalGoalRepository.save(totalGoal);

        // 카테고리 별로 목표 저장
        for (GoalByCategoryDetailResponseDto goalByCategoryDetailResponseDto : goalByCategoryList) {
            Optional<Category> categoryOptional = categoryRepository.findById(goalByCategoryDetailResponseDto.getCategoryId());

            // 카테고리 정보 체크
            if (!categoryOptional.isPresent()) {
                throw new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND);
            }

            GoalByCategory goalByCategory = GoalByCategory.builder()
                    .totalGoal(totalGoal)
                    .category(categoryOptional.get())
                    .categoryGoalAmount(goalByCategoryDetailResponseDto.getCategoryGoalAmount())
                    .build();

            goalByCategoryRepository.save(goalByCategory);
        }
    }

    /**
     * 목표 조회 메서드
     *
     * @param memberId
     * @param goalDetailRequestDto
     * @return GoalDetailResponseDto
     * @throws BaseException
     */
    @Override
    public GoalDetailResponseDto detailGoal(UUID memberId, GoalDetailRequestDto goalDetailRequestDto) throws BaseException {
        return null;
    }

    /**
     * 목표 수정 메서드
     *
     * @param memberId
     * @param goalUpdateRequestDto
     * @throws BaseException
     */
    @Override
    public void updateGoal(UUID memberId, GoalUpdateRequestDto goalUpdateRequestDto) throws BaseException {
        Long totalGoalId = goalUpdateRequestDto.getId();
        Integer goalAmount = goalUpdateRequestDto.getGoalAmount();

        Optional<TotalGoal> totalGoalOptional = totalGoalRepository.findById(totalGoalId);

        // 월별 목표 정보 체크
        if (!totalGoalOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.TOTAL_GOAL_NOT_FOUND);
        }

        // 월별 목표 수정
        TotalGoal totalGoal = totalGoalOptional.get();
        TotalGoal newTotalGoal = totalGoal.toBuilder()
                .goalAmount(goalAmount)
                .build();

        totalGoalRepository.save(newTotalGoal);

        // 카테고리 별 목표 수정
        List<GoalByCategoryDetailResponseDto> goalByCategoryList = goalUpdateRequestDto.getGoalByCategoryList();
        for (GoalByCategoryDetailResponseDto goalByCategoryDetailResponseDto : goalByCategoryList) {
            Integer categoryId = goalByCategoryDetailResponseDto.getCategoryId();
            Integer categoryGoalAmount = goalByCategoryDetailResponseDto.getCategoryGoalAmount();

            Optional<Category> category = categoryRepository.findById(categoryId);

            // 카테고리 정보 체크
            if (!category.isPresent()) {
                throw new BaseException(BaseResponseStatus.CATEGORY_NOT_FOUND);
            }

            Optional<GoalByCategory> goalByCategoryOptional = goalByCategoryRepository.findByTotalGoalIdCategoryId(totalGoalId, categoryId);

            // 카테고리 별 목표 체크
            if (!goalByCategoryOptional.isPresent()) {
                throw new BaseException(BaseResponseStatus.GOAL_BY_CATEGORY_NOT_FOUND);
            }

            // 카테고리 별 목표 수정
            GoalByCategory goalByCategory = goalByCategoryOptional.get();
            GoalByCategory newGoalByCategory = goalByCategory.toBuilder()
                    .categoryGoalAmount(categoryGoalAmount)
                    .build();

            goalByCategoryRepository.save(newGoalByCategory);
        }
    }
}
