package com.ssafy.namani.domain.transactionInfo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoListResponseDto;
import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;

public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Long> {
    TransactionInfo findTopByAccountInfo_AccountNumberAndTransactionNameLikeOrderByTradeDateDesc(String accountNumber, String transactionName);

    @Query("select new com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoListResponseDto( "
            + "ti.category.id, c.name, "
            + "ti.transactionName, ti.transactionAmount, ti.transactionType, ti.afterBalance, ti.tradeDate) "
            + "from TransactionInfo ti "
            + "left join fetch Category c on ti.category.id = c.id "
            + "where ti.accountInfo.accountNumber = :accountNumber")
    List<TransactionInfoListResponseDto> findByAccountInfo_AccountNumber(@Param("accountNumber") String accountNumber);

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
