package com.ssafy.namani.domain.accountInfo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoCertificationRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoLoginRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoRegistRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoSendCodeRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.response.AccountInfoLoginResponseDto;
import com.ssafy.namani.domain.accountInfo.service.AccountInfoService;
import com.ssafy.namani.domain.member.service.MemberService;
import com.ssafy.namani.domain.jwt.service.JwtService;
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
	private final JwtService jwtService;
	private final MemberService memberService;

	@Autowired
	public AccountInfoController(AccountInfoService accountInfoService,
		BaseResponseService baseResponseService,
		TransactionInfoService transactionInfoService,
		JwtService jwtService,
		MemberService memberService) {
		this.accountInfoService = accountInfoService;
		this.baseResponseService = baseResponseService;
		this.transactionInfoService = transactionInfoService;
		this.jwtService = jwtService;
		this.memberService = memberService;
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

	/**
	 * 사용자가 입력한 토큰 값을 인증하는 API입니다.
	 * @param token
	 * @param accountInfoCertificationRequestDto
	 * @return
	 */
	@PostMapping("/certification")
	public BaseResponse<Object> certAuthCode(@RequestHeader(value = "accessToken", required = false) String token,
		@RequestBody AccountInfoCertificationRequestDto accountInfoCertificationRequestDto) {
		// 인증 로직
		try {
			accountInfoService.certAccount(accountInfoCertificationRequestDto);
			if (token != null && !token.equals("")) {
				// 로그인 한 유저라면 member_id 연결
				// 인증 + 연결
				String accountNumber = accountInfoCertificationRequestDto.getAccountNumber();
				UUID memberIdFromToken = jwtService.getMemberIdFromToken(token);
				memberService.connectAccountWithMember(accountNumber, memberIdFromToken);
			}
			return baseResponseService.getSuccessNoDataResponse();
		} catch (BaseException e) {
			return baseResponseService.getFailureResponse(BaseResponseStatus.INVALID_AUTHORIZATION_NUMBER);
		}
	}

	/**
	 * 계좌 정보로 로그인 하는 API입니다.
	 * @param accountInfoLoginRequestDto
	 * @return
	 */
	@PostMapping("/login")
	public BaseResponse<Object> accountLogin(@RequestBody AccountInfoLoginRequestDto accountInfoLoginRequestDto) {
		try {
			AccountInfoLoginResponseDto accountInfoLoginResponseDto = accountInfoService.loginAccount(
				accountInfoLoginRequestDto);
			return baseResponseService.getSuccessResponse(accountInfoLoginResponseDto);
		} catch (BaseException e) {
			// 계좌 로그인 실패
			return baseResponseService.getFailureResponse(BaseResponseStatus.ACCOUNT_LOGIN_FAIL);
		}
	}
}
