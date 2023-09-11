package com.ssafy.namani.domain.transactionInfo.service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoSendCodeRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoRegistRequestDto;
import com.ssafy.namani.global.response.BaseException;

public interface TransactionInfoService {
	void addTransaction(TransactionInfoRegistRequestDto transactionInfoRegistRequestDto);

	void addTransaction(AccountInfoSendCodeRequestDto accountInfoSendCodeRequestDto) throws BaseException;
}
