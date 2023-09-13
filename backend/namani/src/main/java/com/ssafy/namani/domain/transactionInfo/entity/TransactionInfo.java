package com.ssafy.namani.domain.transactionInfo.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.emotion.entity.Emotion;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class TransactionInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 거래내역 아이디

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_number")
	private AccountInfo accountInfo; // 계좌번호

	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category; // 카테고리 아이디

	@OneToOne
	@JoinColumn(name = "emotion_id")
	private Emotion emotion; // 이모션 아이디

	private String transactionName; // 거래내역명
	private Integer transactionAmount; // 거래금액
	private Integer transactionType; // 거래종류 1 : 입금  2 : 출금
	private Integer afterBalance; // 거래 후 잔액

	@CreationTimestamp
	private Timestamp tradeDate; // 거래 일자

	@Builder
	public TransactionInfo(Long id, AccountInfo accountInfo, Category category, Emotion emotion, String transactionName,
		Integer transactionAmount, Integer transactionType, Integer afterBalance, Timestamp tradeDate) {
		this.id = id;
		this.accountInfo = accountInfo;
		this.category = category;
		this.emotion = emotion;
		this.transactionName = transactionName;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.afterBalance = afterBalance;
		this.tradeDate = tradeDate;
	}
}
