package com.ssafy.namani.domain.statistic.entity;

import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.member.entity.Member;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DailyStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 일간 통계 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category; // 카테고리

    @NotNull
    private Integer amount; // 통계값

    @NotNull
    private Timestamp regDate; // 통계 등록 날짜

    @Builder(toBuilder = true)
    public DailyStatistic(Long id, Member member, Category category, Integer amount, Timestamp regDate) {
        this.id = id;
        this.member = member;
        this.category = category;
        this.amount = amount;
        this.regDate = regDate;
    }
}
