package com.ssafy.namani.domain.accountInfo.service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoCertificationRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoLoginRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoRegistRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.response.AccountInfoLoginResponseDto;
import com.ssafy.namani.global.response.BaseException;

public interface AccountInfoService {
	void addAccount(AccountInfoRegistRequestDto accountInfoRegistRequestDto) throws BaseException;

	void certAccount(AccountInfoCertificationRequestDto accountInfoCertificationRequestDto) throws BaseException;

	AccountInfoLoginResponseDto loginAccount(AccountInfoLoginRequestDto accountInfoLoginRequestDto) throws BaseException;
}
