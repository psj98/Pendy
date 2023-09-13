package com.ssafy.namani.domain.transactionInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long> {
    TransactionInfo findTopByAccountInfo_AccountNumberAndTransactionNameLikeOrderByTradeDateDesc(String accountNumber, String transactionName);

    /**
     * 계좌 번호로 현재까지의 거래내역을 가져오는 메서드
     *
     * @param accountNumber
     * @return
     */
    @Query(value = "SELECT * FROM transaction_info t " +
            "WHERE t.account_number = ?1", nativeQuery = true)
    Optional<List<TransactionInfo>> findAllByAccountNumber(String accountNumber);
}
