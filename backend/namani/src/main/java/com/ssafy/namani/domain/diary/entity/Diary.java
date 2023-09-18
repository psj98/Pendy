package com.ssafy.namani.domain.diary.entity;

import com.ssafy.namani.domain.member.entity.Member;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Timestamp regDate; // 일기 등록 날짜

    @Builder
    public Diary(Long id, Member member, String content, String comment, Integer stampType, Timestamp regDate) {
        this.id = id;
        this.member = member;
        this.content = content;
        this.comment = comment;
        this.stampType = stampType;
        this.regDate = regDate;
    }
}
