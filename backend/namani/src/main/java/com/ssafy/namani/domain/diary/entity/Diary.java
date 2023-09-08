package com.ssafy.namani.domain.diary.entity;

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
public class Diary {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id; // 일기 아이디

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 사용자

    @NotNull
    private String content; // 일기 내용

    @NotNull
    private String comment; // 일기 코멘트

    @NotNull
    private Integer stampType; // 일기 도장 종류

    @NotNull
    private Date regDate; // 일기 등록 날짜
}
