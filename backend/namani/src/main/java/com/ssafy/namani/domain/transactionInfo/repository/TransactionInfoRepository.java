package com.ssafy.namani.domain.transactionInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long> {
	TransactionInfo findTopByAccountInfo_AccountNumberAndTransactionNameLikeOrderByTradeDateDesc(String accountNumber, String transactionName);
}
