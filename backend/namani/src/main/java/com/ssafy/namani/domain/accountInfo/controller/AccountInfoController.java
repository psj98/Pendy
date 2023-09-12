package com.ssafy.namani.domain.accountInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoRegistRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoSendCodeRequestDto;
import com.ssafy.namani.domain.accountInfo.service.AccountInfoService;
import com.ssafy.namani.domain.transactionInfo.service.TransactionInfoService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import com.ssafy.namani.global.response.BaseResponseStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/accounts")
public class AccountInfoController {
	private final AccountInfoService accountInfoService;
	private final BaseResponseService baseResponseService;

	private final TransactionInfoService transactionInfoService;

	@Autowired
	public AccountInfoController(AccountInfoService accountInfoService,
		BaseResponseService baseResponseService,
		TransactionInfoService transactionInfoService) {
		this.accountInfoService = accountInfoService;
		this.baseResponseService = baseResponseService;
		this.transactionInfoService = transactionInfoService;
	}

	/**
	 * 계좌를 생성하는 API입니다.
	 * @param accountInfoRegistRequestDto
	 * @return
	 */
	@PostMapping("/regist")
	public BaseResponse<Object> addAccount(
		@RequestBody AccountInfoRegistRequestDto accountInfoRegistRequestDto) {
		log.debug("계좌 생성");
		try {
			// 계좌 정보 추가
			accountInfoService.addAccount(accountInfoRegistRequestDto);
			return baseResponseService.getSuccessNoDataResponse();
		} catch (Exception e) {
			// 중복되는 계좌번호
			return baseResponseService.getFailureResponse(BaseResponseStatus.CONFLICK_ACCOUNT_NUMBER);
		}
	}

	/**
	 * 계좌 인증을 위해 1원을 송금하는 API입니다.
	 * @param accountInfoSendCodeRequestDto
	 * @return
	 */
	@PostMapping("/send-code")
	public BaseResponse<Object> sendCode(@RequestBody AccountInfoSendCodeRequestDto accountInfoSendCodeRequestDto) {
		log.debug("1원 송금");
		try {
			transactionInfoService.addTransaction(accountInfoSendCodeRequestDto);
			return baseResponseService.getSuccessNoDataResponse();
		} catch (BaseException e) {
			return baseResponseService.getFailureResponse(BaseResponseStatus.ACCOUNT_NOT_FOUND);
		}
	}
}
