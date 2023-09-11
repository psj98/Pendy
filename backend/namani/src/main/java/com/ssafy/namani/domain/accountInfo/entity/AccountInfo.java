package com.ssafy.namani.domain.accountInfo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ssafy.namani.domain.bank.entity.Bank;
import com.ssafy.namani.domain.member.entity.Member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AccountInfo {
	@Id
	private String accountNumber; // 계좌 번호

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; // 멤버 아이디

	@OneToOne
	@JoinColumn(name = "bank_code")
	private Bank bank; // 은행 코드

	private Integer accountPassword; // 계좌 비밀번호

	private Integer balance; // 잔액

	public void updateMemberId(Member member){
		this.member = member;
	}
}
