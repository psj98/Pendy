package com.ssafy.namani.domain.accountInfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoRegistRequestDto;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.accountInfo.repository.AccountInfoRepository;
import com.ssafy.namani.domain.bank.entity.Bank;
import com.ssafy.namani.domain.bank.repository.BankRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;

@Service
public class AccountInfoServiceImpl implements AccountInfoService {
	private final AccountInfoRepository accountInfoRepository;
	private final BankRepository bankRepository;

	@Autowired
	public AccountInfoServiceImpl(AccountInfoRepository accountInfoRepository,
		BankRepository bankRepository) {
		this.accountInfoRepository = accountInfoRepository;
		this.bankRepository = bankRepository;
	}

	@Override
	public void addAccount(AccountInfoRegistRequestDto accountInfoRegistRequestDto) throws BaseException {
		// bankCode에 해당하는 Bank정보 있는지 확인
		Optional<Bank> byId = bankRepository.findById(accountInfoRegistRequestDto.getBankCode());
		if (byId.isPresent()) {
			// Dto -> Entity 변환
			AccountInfo accountInfo = AccountInfo.builder()
				.accountNumber(accountInfoRegistRequestDto.getAccountNumber())
				.bank(byId.get())
				.accountPassword(accountInfoRegistRequestDto.getAccountPassword())
				.balance(accountInfoRegistRequestDto.getBalance())
				.build();

			// 등록하려는 계좌번호가 이미 DB에 존재하는 경우 예외 처리
			if (accountInfoRepository.findById(accountInfo.getAccountNumber()).isPresent()) {
				throw new BaseException(BaseResponseStatus.CONFLICK_ACCOUNT_NUMBER);
			}
			// 계좌 정보 저장
			accountInfoRepository.save(accountInfo);
		}
	}
}
