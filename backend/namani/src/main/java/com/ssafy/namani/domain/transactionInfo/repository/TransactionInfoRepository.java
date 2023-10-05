package com.ssafy.namani.domain.transactionInfo.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ssafy.namani.domain.transactionInfo.entity.ITransactionInfoList;
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

    /**
     * 계좌 번호로 현재까지의 거래내역을 가져오는 메서드
     *
     * @param accountNumber
     * @return
     */
    @Query(value = "SELECT * FROM transaction_info t " +
            "WHERE t.account_number = ?1", nativeQuery = true)
    Optional<List<TransactionInfo>> findAllByAccountNumber(String accountNumber);

    /**
     * 계좌번호 + 등록 시간으로 추가된 거래내역을 가져오는 메서드
     *
     * @param accountNumber
     * @param transactionType
     * @param lastRegDate
     * @param curDate
     * @return
     */
    @Query(value = "SELECT * FROM transaction_info t " +
            "WHERE t.account_number = ?1 AND t.transaction_type = ?2 " +
            "AND t.trade_date between ?3 and ?4", nativeQuery = true)
    Optional<List<TransactionInfo>> findAllWithdrawalsByAccountNumber(String accountNumber, Integer transactionType,
                                                                      Timestamp lastRegDate, Timestamp curDate);

    /**
     * 사용자 아이디 + 현재 일에 해당하는 모든 거래 내역을 카테고리 별로 가져오기 => [카테고리 정보, 총합]
     *
     * @param memberId
     * @param curDate
     * @return
     */
    @Query(value = "SELECT c.id AS categoryId, c.name AS categoryName, SUM(t.transaction_amount) AS amount FROM transaction_info t, category c " +
            "WHERE c.id = t.category_id " +
            "AND t.account_number IN (SELECT account_number FROM account_info" +
            "                         WHERE member_id = ?1) " +
            "AND DATE_FORMAT(t.trade_date, '%Y-%m-%d') = DATE_FORMAT(?2, '%Y-%m-%d') " +
            "GROUP BY t.category_id;", nativeQuery = true)
    Optional<List<ITransactionInfoList>> findDailyStatisticByMemberIdAccountNumberRegDate(UUID memberId, Timestamp curDate);

    /**
     * 사용자 아이디 + 현재 월에 해당하는 모든 거래 내역을 카테고리 별로 가져오기 => [카테고리 정보, 총합]
     *
     * @param memberId
     * @param curDate
     * @return
     */
    @Query(value = "SELECT c.id AS categoryId, c.name AS categoryName, SUM(t.transaction_amount) AS amount " +
            "FROM transaction_info t, category c " +
            "WHERE c.id = t.category_id " +
            "AND t.account_number IN (SELECT account_number FROM account_info" +
            "                         WHERE member_id = ?1) " +
            "AND DATE_FORMAT(t.trade_date, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m') " +
            "GROUP BY t.category_id;", nativeQuery = true)
    Optional<List<ITransactionInfoList>> findMonthlyStatisticByMemberIdAccountNumberRegDate(UUID memberId, Timestamp curDate);

    /**
     * 3달간 소비내역 통계 조회하기
     *
     * @param memberId
     * @param curDate
     * @return Optional<List<ITransactionInfoList>>
     */
    @Query(value = "SELECT c.id AS categoryId, c.name AS categoryName, SUM(t.transaction_amount) AS amount " +
            "FROM transaction_info t, category c " +
            "WHERE c.id = t.category_id " +
            "AND t.account_number IN (SELECT account_number FROM account_info " +
            "                         WHERE member_id = ?1) " +
            "AND DATE_FORMAT(t.trade_date, '%Y-%m') BETWEEN DATE_FORMAT(DATE_SUB(?2, INTERVAL 3 MONTH), '%Y-%m') " +
            "AND DATE_FORMAT(DATE_SUB(?2, INTERVAL 1 MONTH), '%Y-%m') " +
            "GROUP BY t.category_id;", nativeQuery = true)
    Optional<List<ITransactionInfoList>> findMonthlyStatisticByMemberIdAccountNumberRegDateForThreeMonth(UUID memberId, Timestamp curDate);

    /**
     * 사용자 아이디 + 현재 일자로 모든 거래 내역 가져오기
     *
     * @param memberId
     * @param curDate
     * @return Optional<List<TransactionInfo>>
     */
    @Query(value = "SELECT * FROM transaction_info t " +
            "WHERE t.account_number IN (SELECT account_number FROM account_info " +
            "                         WHERE member_id = ?1) " +
            "AND DATE_FORMAT(t.trade_date, '%Y-%m-%d') = DATE_FORMAT(?2, '%Y-%m-%d') " +
            "AND t.transaction_type = 2", nativeQuery = true)
    Optional<List<TransactionInfo>> findTransactionInfoByMemberIdCurDate(UUID memberId, Timestamp curDate);
}
