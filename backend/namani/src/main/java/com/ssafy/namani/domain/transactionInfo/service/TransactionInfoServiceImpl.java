package com.ssafy.namani.domain.transactionInfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoSendCodeRequestDto;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.accountInfo.repository.AccountInfoRepository;
import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoRegistRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoRegistResponseDto;
import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;
import com.ssafy.namani.domain.transactionInfo.repository.TransactionInfoRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;

@Service
public class TransactionInfoServiceImpl implements TransactionInfoService {
	private final TransactionInfoRepository transactionInfoRepository;
	private final AccountInfoRepository accountInfoRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public TransactionInfoServiceImpl(TransactionInfoRepository transactionInfoRepository,
		AccountInfoRepository accountInfoRepository,
		CategoryRepository categoryRepository) {
		this.transactionInfoRepository = transactionInfoRepository;
		this.accountInfoRepository = accountInfoRepository;
		this.categoryRepository = categoryRepository;
	}

	/**
	 * 계좌 거래내역 생성
	 * @param transactionInfoRegistRequestDto
	 */
	@Override
	public TransactionInfoRegistResponseDto addTransaction(
		TransactionInfoRegistRequestDto transactionInfoRegistRequestDto) throws BaseException {
		Optional<AccountInfo> byId = accountInfoRepository.findById(transactionInfoRegistRequestDto.getAccountNumber());
		// 거래내역을 추가하려는 계좌번호가 있는지 확인
		if (byId.isPresent()) {
			AccountInfo accountInfo = byId.get();
			TransactionInfo transactionInfo;
			// 출금인경우
			if (transactionInfoRegistRequestDto.getCategoryId() != null) {
				Optional<Category> category = categoryRepository.findById(
					transactionInfoRegistRequestDto.getCategoryId());
				transactionInfo = TransactionInfo.builder()
					.accountInfo(accountInfo)
					.category(category.get())
					.transactionName(transactionInfoRegistRequestDto.getTransactionName())
					.transactionAmount(transactionInfoRegistRequestDto.getTransactionAmount())
					.transactionType(transactionInfoRegistRequestDto.getTransactionType())
					.afterBalance(accountInfo.getBalance() - transactionInfoRegistRequestDto.getTransactionAmount())
					.build();
			} else { // 입금인 경우
				transactionInfo = TransactionInfo.builder()
					.accountInfo(accountInfo)
					.transactionName(transactionInfoRegistRequestDto.getTransactionName())
					.transactionAmount(transactionInfoRegistRequestDto.getTransactionAmount())
					.transactionType(transactionInfoRegistRequestDto.getTransactionType())
					.afterBalance(accountInfo.getBalance() + transactionInfoRegistRequestDto.getTransactionAmount())
					.build();
			}
			// 계좌 잔액 업데이트
			accountInfo.updateBalance(transactionInfoRegistRequestDto.getTransactionType(),
				transactionInfoRegistRequestDto.getTransactionAmount());
			transactionInfoRepository.save(transactionInfo);
			int newBalance = accountInfo.getBalance();
			return new TransactionInfoRegistResponseDto(Integer.toString(newBalance));
		} else {
			// 계좌번호 존재하지않음
			throw new BaseException(BaseResponseStatus.ACCOUNT_NOT_FOUND);
		}
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
