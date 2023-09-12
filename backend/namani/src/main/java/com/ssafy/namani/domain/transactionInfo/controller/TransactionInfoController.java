package com.ssafy.namani.domain.transactionInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoRegistRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoRegistResponseDto;
import com.ssafy.namani.domain.transactionInfo.service.TransactionInfoService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import com.ssafy.namani.global.response.BaseResponseStatus;

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
			return baseResponseService.getFailureResponse(BaseResponseStatus.ACCOUNT_NOT_FOUND);
		}
	}
}
