package com.ssafy.namani.domain.diary.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.ssafy.namani.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
