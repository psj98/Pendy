package com.ssafy.namani.domain.transactionInfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoSendCodeRequestDto;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.accountInfo.repository.AccountInfoRepository;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoRegistRequestDto;
import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;
import com.ssafy.namani.domain.transactionInfo.repository.TransactionInfoRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;

@Service
public class TransactionInfoServiceImpl implements TransactionInfoService {
	private final TransactionInfoRepository transactionInfoRepository;
	private final AccountInfoRepository accountInfoRepository;

	@Autowired
	public TransactionInfoServiceImpl(TransactionInfoRepository transactionInfoRepository,
		AccountInfoRepository accountInfoRepository) {
		this.transactionInfoRepository = transactionInfoRepository;
		this.accountInfoRepository = accountInfoRepository;
	}

	/**
	 * 계좌 거래내역 생성
	 * @param transactionInfoRegistRequestDto
	 */
	@Override
	public void addTransaction(TransactionInfoRegistRequestDto transactionInfoRegistRequestDto) {

	}

	/**
	 * 1원 송금
	 * @param accountInfoSendCodeRequestDto
	 */
	@Override
	public void addTransaction(AccountInfoSendCodeRequestDto accountInfoSendCodeRequestDto) throws BaseException {
		Optional<AccountInfo> byId = accountInfoRepository.findById(accountInfoSendCodeRequestDto.getAccountNumber());
		// 거래내역을 추가하려는 계좌번호가 있는지 확인
		if (byId.isPresent()) {
			// 랜덤3자리 숫자 발급
			int randomNum = (int)(Math.random() * 899) + 100;
			AccountInfo accountInfo = byId.get();
			TransactionInfo transactionInfo = TransactionInfo.builder()
				.accountInfo(accountInfo)
				.transactionName("Pendy" + randomNum)
				.transactionAmount(1)
				.transactionType(1)
				.afterBalance(accountInfo.getBalance() + 1)
				.build();

			// 계좌 잔액 업데이트
			accountInfo.updateBalance(1, 1);
			transactionInfoRepository.save(transactionInfo);
		} else {
			// 계좌번호 존재하지않음
			throw new BaseException(BaseResponseStatus.ACCOUNT_NOT_FOUND);
		}
	}
}
