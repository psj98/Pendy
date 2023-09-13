package com.ssafy.namani.domain.accountInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, String> {
}
