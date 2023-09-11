package com.ssafy.namani.domain.accountInfo;

import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountInfoRepository extends JpaRepository<AccountInfo, String> {
}
