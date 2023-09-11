package com.ssafy.namani.domain.accountInfo.service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoRegistRequestDto;

public interface AccountInfoService {
	void addAccount(AccountInfoRegistRequestDto accountInfoRegistRequestDto) throws Exception;
}
