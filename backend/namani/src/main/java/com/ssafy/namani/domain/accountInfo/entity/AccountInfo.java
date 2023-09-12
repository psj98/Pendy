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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

	@Builder
	public AccountInfo(String accountNumber, Bank bank, Integer accountPassword, Integer balance) {
		this.accountNumber = accountNumber;
		this.bank = bank;
		this.accountPassword = accountPassword;
		this.balance = balance;
	}

	/**
	 * 잔액 업데이트
	 * @param type
	 * @param amount
	 */
	public void updateBalance(Integer type, Integer amount) {
		// 입금이면 잔액 추가
		if (type == 1) {
			this.balance += amount;
		} else { // 출금이면 잔액 감소
			this.balance -= amount;
		}
	}
}
