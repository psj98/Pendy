package com.ssafy.namani.domain.accountInfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountInfo {
	@Id
	private String accountNumber;

	public AccountInfo(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
