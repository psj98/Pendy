package com.ssafy.namani.domain.transactionInfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoListRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoRegistRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoListResponseDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoRegistResponseDto;
import com.ssafy.namani.domain.transactionInfo.service.TransactionInfoService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/transactions")
public class TransactionInfoController {
	private final TransactionInfoService transactionInfoService;
	private final BaseResponseService baseResponseService;

	@Autowired
	public TransactionInfoController(TransactionInfoService transactionInfoService,
		BaseResponseService baseResponseService) {
		this.transactionInfoService = transactionInfoService;
		this.baseResponseService = baseResponseService;
	}

	@PostMapping("/regist")
	public BaseResponse<Object> addTransaction(
		@RequestBody TransactionInfoRegistRequestDto transactionInfoRegistRequestDto) {
		try {
			TransactionInfoRegistResponseDto transactionInfoRegistResponseDto = transactionInfoService.addTransaction(
				transactionInfoRegistRequestDto);
			return baseResponseService.getSuccessResponse(transactionInfoRegistResponseDto);
		} catch (BaseException e) {
			return baseResponseService.getFailureResponse(e.status);
		}
	}

	/**
	 * 계좌번호에 해당하는 거래내역을 조회하는 API입니다.
	 * @param transactionInfoListRequestDto
	 * @return
	 */
	@PostMapping("/list")
	public BaseResponse<Object> getTransactionList(
		@RequestBody TransactionInfoListRequestDto transactionInfoListRequestDto) {
		try {
			List<TransactionInfoListResponseDto> transactionInfoList = transactionInfoService.getTransactionInfoList(
				transactionInfoListRequestDto);
			return baseResponseService.getSuccessResponse(transactionInfoList);
		} catch (BaseException e) {
			return baseResponseService.getFailureResponse(e.status);
		}
	}
}
