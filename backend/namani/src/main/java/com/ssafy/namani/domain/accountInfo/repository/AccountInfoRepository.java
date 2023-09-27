package com.ssafy.namani.domain.accountInfo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import org.springframework.data.jpa.repository.Query;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, String> {
    Optional<AccountInfo> findByAccountNumberAndAccountPassword(String accountNumber, Integer accountPassword);

    List<AccountInfo> findByMember_Id(UUID memberId);

    /**
     * 계좌 정보, 은행 코드로 계좌 정보가 있는지 확인
     *
     * @param accountNumber
     * @param bankCode
     * @return
     */
    @Query(value = "SELECT * FROM account_info " +
            "WHERE member_id IS NULL " +
            "AND account_number = ?1 " +
            "AND bank_code = ?2", nativeQuery = true)
    Optional<AccountInfo> findByAccountNumberBankCodeMemberId(String accountNumber, String bankCode);
}
