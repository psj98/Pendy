package com.ssafy.namani.domain.accountInfo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, String> {
	Optional<AccountInfo> findByAccountNumberAndAccountPassword(String accountNumber, Integer accountPassword);
}
