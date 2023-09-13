package com.ssafy.namani.domain.transactionInfo.service;

import java.util.List;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoSendCodeRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoListRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoRegistRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoListResponseDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoRegistResponseDto;
import com.ssafy.namani.global.response.BaseException;

public interface TransactionInfoService {
	TransactionInfoRegistResponseDto addTransaction(TransactionInfoRegistRequestDto transactionInfoRegistRequestDto) throws BaseException;

	void addTransaction(AccountInfoSendCodeRequestDto accountInfoSendCodeRequestDto) throws BaseException;

	List<TransactionInfoListResponseDto> getTransactionInfoList(TransactionInfoListRequestDto transactionInfoListRequestDto) throws BaseException;
}
