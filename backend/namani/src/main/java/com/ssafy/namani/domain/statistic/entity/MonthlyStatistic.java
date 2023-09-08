package com.ssafy.namani.domain.statistic.entity;

import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.member.entity.Member;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyStatistic {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id; // 월간 통계 id

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category; // 카테고리

    @NotNull
    private Integer amount; // 통계값

    @NotNull
    private Date regDate; // 통계 등록 날짜
}
