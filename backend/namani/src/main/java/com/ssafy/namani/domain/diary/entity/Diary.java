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
	private String title; // 일기 제목

	@NotNull
	private String content; // 일기 내용

	@NotNull
	private String comment; // 일기 코멘트

	@NotNull
	private Integer stampType; // 일기 도장 종류

//	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp regDate; // 일기 등록 날짜

	@Builder(toBuilder = true)
	public Diary(Long id, Member member, String title, String content, String comment, Integer stampType, Timestamp regDate) {
		this.id = id;
		this.member = member;
		this.title = title;
		this.content = content;
		this.comment = comment;
		this.stampType = stampType;
		this.regDate = regDate;
	}
}
