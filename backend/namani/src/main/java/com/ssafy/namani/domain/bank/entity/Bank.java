package com.ssafy.namani.domain.bank.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Bank {
	@Id
	private String bankCode; // 은행 코드
	private String bankName; // 은행명
}
