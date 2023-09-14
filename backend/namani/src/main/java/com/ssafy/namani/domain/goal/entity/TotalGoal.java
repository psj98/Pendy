package com.ssafy.namani.domain.goal.entity;

import com.ssafy.namani.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TotalGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 목표 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 사용자

    @NotNull
    private Integer goalAmount; // 목표 소비 금액

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp goalDate; // 목표 설정 날짜

    private String aiAnalysis; // AI 분석

    @Builder
    public TotalGoal(Long id, Member member, Integer goalAmount, Timestamp goalDate, String aiAnalysis) {
        this.id = id;
        this.member = member;
        this.goalAmount = goalAmount;
        this.goalDate = goalDate;
        this.aiAnalysis = aiAnalysis;
    }
}
