package com.ssafy.namani.domain.accountInfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoCertificationRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoLoginRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoRegistRequestDto;
import com.ssafy.namani.domain.accountInfo.dto.response.AccountInfoLoginResponseDto;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.accountInfo.repository.AccountInfoRepository;
import com.ssafy.namani.domain.bank.entity.Bank;
import com.ssafy.namani.domain.bank.repository.BankRepository;
import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;
import com.ssafy.namani.domain.transactionInfo.repository.TransactionInfoRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
	private final AccountInfoRepository accountInfoRepository;
	private final BankRepository bankRepository;
	private final TransactionInfoRepository transactionInfoRepository;

	@Autowired
	public AccountInfoServiceImpl(AccountInfoRepository accountInfoRepository,
		BankRepository bankRepository,
		TransactionInfoRepository transactionInfoRepository) {
		this.accountInfoRepository = accountInfoRepository;
		this.bankRepository = bankRepository;
		this.transactionInfoRepository = transactionInfoRepository;
	}

	@Override
	public void addAccount(AccountInfoRegistRequestDto accountInfoRegistRequestDto) throws BaseException {
		// bankCode에 해당하는 Bank정보 있는지 확인
		Optional<Bank> byId = bankRepository.findById(accountInfoRegistRequestDto.getBankCode());

		// 은행 정보 존재하지 않음
		if (byId.isEmpty()) {
			throw new BaseException(BaseResponseStatus.BANK_CODE_NOT_FOUND);
		}

		// 등록하려는 계좌번호가 이미 DB에 존재하는 경우 예외 처리
		Optional<AccountInfo> byId1 = accountInfoRepository.findById(accountInfoRegistRequestDto.getAccountNumber());

		if (byId1.isPresent()) {
			throw new BaseException(BaseResponseStatus.CONFLICK_ACCOUNT_NUMBER);
		}

		// Dto -> Entity 변환
		AccountInfo accountInfo = AccountInfo.builder()
			.accountNumber(accountInfoRegistRequestDto.getAccountNumber())
			.bank(byId.get())
			.accountPassword(accountInfoRegistRequestDto.getAccountPassword())
			.balance(accountInfoRegistRequestDto.getBalance())
			.build();

		// 계좌 정보 저장
		accountInfoRepository.save(accountInfo);
	}

	@Override
	public void certAccount(AccountInfoCertificationRequestDto accountInfoCertificationRequestDto) throws
		BaseException {
		// 사용자가 입력한 인증 코드
		String authCode = accountInfoCertificationRequestDto.getAuthCode();
		// 사용자가 입력한 계좌 번호
		String accountNumber = accountInfoCertificationRequestDto.getAccountNumber();

		// 계좌 번호의 거래내역에서 거래명이 Pendy로 시작하는 가장 최근 거래내역 찾음
		TransactionInfo transactionInfo = transactionInfoRepository.findTopByAccountInfo_AccountNumberAndTransactionNameLikeOrderByTradeDateDesc(
			accountNumber, "Pendy%");

		String transactionName = transactionInfo.getTransactionName();

		if (transactionName != null && transactionName.length() > 5) {
			if (!transactionName.substring(5).equals(authCode)) {
				// 예외 처리
				throw new BaseException(BaseResponseStatus.INVALID_AUTHORIZATION_NUMBER);
			}
		} else {
			throw new BaseException(BaseResponseStatus.INVALID_AUTHORIZATION_NUMBER);
		}
	}

	/**
	 * 계좌정보로 로그인합니다.
	 * @param accountInfoLoginRequestDto
	 * @return
	 */
	@Override
	public AccountInfoLoginResponseDto loginAccount(AccountInfoLoginRequestDto accountInfoLoginRequestDto) throws
		BaseException {
		String accountNumber = accountInfoLoginRequestDto.getAccountNumber();
		Integer accountPassword = accountInfoLoginRequestDto.getAccountPassword();

		Optional<AccountInfo> byIdAndAccountPassword = accountInfoRepository.findByAccountNumberAndAccountPassword(
			accountNumber,
			accountPassword);
		// 로그인하려는 계좌가 등록되어있음
		if (byIdAndAccountPassword.isPresent()) {
			AccountInfo accountInfo = byIdAndAccountPassword.get();
			return AccountInfoLoginResponseDto.builder()
				.accountNumber(accountInfo.getAccountNumber())
				.bankCode(accountInfo.getBank().getBankCode())
				.balance(accountInfo.getBalance())
				.build();
		}
		throw new BaseException(BaseResponseStatus.ACCOUNT_LOGIN_FAIL);
	}
}
