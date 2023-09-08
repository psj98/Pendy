package com.ssafy.namani.domain.bank.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Bank {
	@Id
	String bankCode;
	String bankName;
}
