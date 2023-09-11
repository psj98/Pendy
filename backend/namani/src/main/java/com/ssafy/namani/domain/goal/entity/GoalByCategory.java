package com.ssafy.namani.domain.goal.entity;

import com.ssafy.namani.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalByCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 카테고리 별 목표 아이디

    @ManyToOne
    @JoinColumn(name = "total_goal_id")
    private TotalGoal totalGoal; // 전체 목표

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category; // 카테고리

    @NotNull
    private Integer categoryGoalAmount; // 목표 소비 금액
}
