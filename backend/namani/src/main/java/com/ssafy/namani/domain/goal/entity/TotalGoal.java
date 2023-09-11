package com.ssafy.namani.domain.goal.entity;

import com.ssafy.namani.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 목표 아이디

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 사용자

    @NotNull
    private Integer goalAmount; // 목표 소비 금액

    @NotNull
    private Date goalDate; // 목표 설정 날짜

    @NotNull
    private String aiAnalysis; // AI 분석
}
