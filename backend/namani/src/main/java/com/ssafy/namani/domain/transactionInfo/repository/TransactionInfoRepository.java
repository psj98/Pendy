package com.ssafy.namani.domain.transactionInfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoListResponseDto;
import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long> {
	TransactionInfo findTopByAccountInfo_AccountNumberAndTransactionNameLikeOrderByTradeDateDesc(String accountNumber,
		String transactionName);

	@Query("select new com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoListResponseDto( "
		+ "ti.category.id, c.name, "
		+ "ti.transactionName, ti.transactionAmount, ti.transactionType, ti.afterBalance, ti.tradeDate) "
		+ "from TransactionInfo ti "
		+ "left join fetch Category c on ti.category.id = c.id "
		+ "where ti.accountInfo.accountNumber = :accountNumber")
	List<TransactionInfoListResponseDto> findByAccountInfo_AccountNumber(@Param("accountNumber") String accountNumber);
}