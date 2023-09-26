package com.ssafy.namani.domain.transactionInfo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ssafy.namani.domain.clovaOCR.dto.response.ClovaOCRResponseDto;
import com.ssafy.namani.domain.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.namani.domain.accountInfo.dto.request.AccountInfoSendCodeRequestDto;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.accountInfo.repository.AccountInfoRepository;
import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.ageSalary.repository.AgeSalaryRepository;
import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import com.ssafy.namani.domain.avgConsumptionAmount.repository.AvgConsumptionAmountRepository;
import com.ssafy.namani.domain.category.entity.Category;
import com.ssafy.namani.domain.category.repository.CategoryRepository;
import com.ssafy.namani.domain.emotion.entity.Emotion;
import com.ssafy.namani.domain.emotion.repository.EmotionRepository;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoListRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoRegistRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.request.TransactionInfoTodayListRequestDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoListResponseDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoRegistResponseDto;
import com.ssafy.namani.domain.transactionInfo.dto.response.TransactionInfoTodayDto;
import com.ssafy.namani.domain.transactionInfo.entity.TransactionInfo;
import com.ssafy.namani.domain.transactionInfo.repository.TransactionInfoRepository;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionInfoServiceImpl implements TransactionInfoService {
	private final TransactionInfoRepository transactionInfoRepository;
	private final AccountInfoRepository accountInfoRepository;
	private final CategoryRepository categoryRepository;
	private final AgeSalaryRepository ageSalaryRepository;
	private final AvgConsumptionAmountRepository avgConsumptionAmountRepository;
	private final EmotionRepository emotionRepository;
	private final JwtService jwtService;

	@Autowired
	public TransactionInfoServiceImpl(TransactionInfoRepository transactionInfoRepository,
									  AccountInfoRepository accountInfoRepository,
									  CategoryRepository categoryRepository,
									  AgeSalaryRepository ageSalaryRepository,
									  AvgConsumptionAmountRepository avgConsumptionAmountRepository,
									  EmotionRepository emotionRepository, JwtService jwtService) {
		this.transactionInfoRepository = transactionInfoRepository;
		this.accountInfoRepository = accountInfoRepository;
		this.categoryRepository = categoryRepository;
		this.ageSalaryRepository = ageSalaryRepository;
		this.avgConsumptionAmountRepository = avgConsumptionAmountRepository;
		this.emotionRepository = emotionRepository;
		this.jwtService = jwtService;
	}

	/**
	 * 계좌 거래내역 생성
	 *
	 * @param transactionInfoRegistRequestDto
	 */
	@Override
	public TransactionInfoRegistResponseDto addTransaction(
		TransactionInfoRegistRequestDto transactionInfoRegistRequestDto) throws BaseException {
		Optional<AccountInfo> byId = accountInfoRepository.findById(transactionInfoRegistRequestDto.getAccountNumber());

		// 거래내역을 추가하려는 계좌번호가 있는지 확인
		if (!byId.isPresent()) {
			throw new BaseException(BaseResponseStatus.ACCOUNT_NOT_FOUND); // 계좌번호 존재하지 않음
		}

		AccountInfo accountInfo = byId.get();
		TransactionInfo transactionInfo;

		AvgConsumptionAmount newAvgConsumptionAmount = null;

		// 출금인 경우
		if (transactionInfoRegistRequestDto.getCategoryId() != null) {
			Optional<Category> category = categoryRepository.findById(transactionInfoRegistRequestDto.getCategoryId());
			Optional<Emotion> emotion = emotionRepository.findById(3);
			transactionInfo = TransactionInfo.builder()
				.accountInfo(accountInfo)
				.category(category.get())
				.emotion(emotion.get())
				.transactionName(transactionInfoRegistRequestDto.getTransactionName())
				.transactionAmount(transactionInfoRegistRequestDto.getTransactionAmount())
				.transactionType(transactionInfoRegistRequestDto.getTransactionType())
				.afterBalance(accountInfo.getBalance() - transactionInfoRegistRequestDto.getTransactionAmount())
				.build();

			if (accountInfo.getMember() != null) {
				Integer age = accountInfo.getMember().getAge() / 10 * 10;
				Integer salary = accountInfo.getMember().getSalary() / 10000000 * 1000;

				// 연령대, 연봉대에 해당하는 정보 조회
				Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age, salary);

				// 연령대, 연봉대에 해당하는 정보 없음
				if (!ageSalaryOptional.isPresent()) {
					throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
				}

				AgeSalary ageSalary = ageSalaryOptional.get();

				// 나이-소득 구간, 카테고리, 현재 연월에 해당하는 평균 소비 정보 조회
				Optional<AvgConsumptionAmount> avgConsumptionAmountOptional = avgConsumptionAmountRepository.findByAgeSalaryIdCategoryId(
					ageSalary.getId(), category.get().getId(), Timestamp.valueOf(LocalDateTime.now()));

				AvgConsumptionAmount avgConsumptionAmount;
				// 나이-소득 구간, 카테고리, 현재 연월에 해당하는 평균 소비 정보 없음
				if (avgConsumptionAmountOptional.isEmpty()) {
					// throw new BaseException(
					// 	BaseResponseStatus.NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY_ID_AND_CATEGORY_ID_AND_REG_DATE);

					avgConsumptionAmount = AvgConsumptionAmount.builder()
						.ageSalary(ageSalary)
						.category(category.get())
						.sumAmount(0)
						.regDate(Timestamp.valueOf(LocalDateTime.now()))
						.build();

					avgConsumptionAmountRepository.save(avgConsumptionAmount);
				} else {
					avgConsumptionAmount = avgConsumptionAmountOptional.get();
				}

				// 평균 소비 정보 총합 수정
				// avgConsumptionAmount = avgConsumptionAmountOptional.get();
				newAvgConsumptionAmount = AvgConsumptionAmount.builder()
					.id(avgConsumptionAmount.getId())
					.ageSalary(avgConsumptionAmount.getAgeSalary())
					.category(avgConsumptionAmount.getCategory())
					.sumAmount(
						avgConsumptionAmount.getSumAmount() + transactionInfoRegistRequestDto.getTransactionAmount())
					.regDate(avgConsumptionAmount.getRegDate())
					.build();
			}
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

		// 출금인 경우에만 평균 소비 정보 업데이트
		if (newAvgConsumptionAmount != null) {
			avgConsumptionAmountRepository.save(newAvgConsumptionAmount); // 평균 소비 정보 업데이트
		}

		int newBalance = accountInfo.getBalance();

		return new TransactionInfoRegistResponseDto(Integer.toString(newBalance));
	}

	/**
	 * ClovaOCR로 추출한 데이터 거래내역에 추가
	 * 
	 * @param token
	 * @param clovaOCRResponseDto
	 */
	@Override
	public void addReceiptTransaction(String token, ClovaOCRResponseDto clovaOCRResponseDto) throws BaseException {
		try{
			if(token == null || token.equals("")) throw new BaseException(BaseResponseStatus.SESSION_EXPIRATION);

			System.out.println(clovaOCRResponseDto.getTotal()+" "+clovaOCRResponseDto.getPlace()+ " "+clovaOCRResponseDto.getDateTime());

			UUID memberId = jwtService.getMemberIdFromToken(token);
			List<AccountInfo> byId = accountInfoRepository.findByMember_Id(memberId);

			if(byId.isEmpty()) throw new BaseException(BaseResponseStatus.ACCOUNT_NOT_FOUND);

			AccountInfo accountInfo = byId.get(0);
			TransactionInfo transactionInfo;

			AvgConsumptionAmount newAvgConsumptionAmount = null;

				// 영수증 데이터는 오프라인 매장으로 정함 (임시)
				Optional<Category> category = categoryRepository.findById(4);
				Optional<Emotion> emotion = emotionRepository.findById(3);
				transactionInfo = TransactionInfo.builder()
						.accountInfo(accountInfo)
						.category(category.get())
						.emotion(emotion.get())
						.transactionName(clovaOCRResponseDto.getPlace())
						.transactionAmount(clovaOCRResponseDto.getTotal())
						.transactionType(2)
						.afterBalance(accountInfo.getBalance() - clovaOCRResponseDto.getTotal())
						.build();

				if (accountInfo.getMember() != null) {
					Integer age = accountInfo.getMember().getAge() / 10 * 10;
					Integer salary = accountInfo.getMember().getSalary() / 10000000 * 1000;

					// 연령대, 연봉대에 해당하는 정보 조회
					Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age, salary);

					// 연령대, 연봉대에 해당하는 정보 없음
					if (!ageSalaryOptional.isPresent()) {
						throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
					}

					AgeSalary ageSalary = ageSalaryOptional.get();

					// 나이-소득 구간, 카테고리, 현재 연월에 해당하는 평균 소비 정보 조회
					Optional<AvgConsumptionAmount> avgConsumptionAmountOptional = avgConsumptionAmountRepository.findByAgeSalaryIdCategoryId(
							ageSalary.getId(), category.get().getId(), Timestamp.valueOf(LocalDateTime.now()));

					AvgConsumptionAmount avgConsumptionAmount;
					// 나이-소득 구간, 카테고리, 현재 연월에 해당하는 평균 소비 정보 없음
					if (avgConsumptionAmountOptional.isEmpty()) {
						// throw new BaseException(
						// 	BaseResponseStatus.NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY_ID_AND_CATEGORY_ID_AND_REG_DATE);

						avgConsumptionAmount = AvgConsumptionAmount.builder()
								.ageSalary(ageSalary)
								.category(category.get())
								.sumAmount(0)
								.regDate(Timestamp.valueOf(LocalDateTime.now()))
								.build();

						avgConsumptionAmountRepository.save(avgConsumptionAmount);
					} else {
						avgConsumptionAmount = avgConsumptionAmountOptional.get();
					}

					// 평균 소비 정보 총합 수정
					// avgConsumptionAmount = avgConsumptionAmountOptional.get();
					newAvgConsumptionAmount = AvgConsumptionAmount.builder()
							.id(avgConsumptionAmount.getId())
							.ageSalary(avgConsumptionAmount.getAgeSalary())
							.category(avgConsumptionAmount.getCategory())
							.sumAmount(
									avgConsumptionAmount.getSumAmount() + clovaOCRResponseDto.getTotal())
							.regDate(avgConsumptionAmount.getRegDate())
							.build();
				}

			// 계좌 잔액 업데이트
			accountInfo.updateBalance(2,
					clovaOCRResponseDto.getTotal());
			transactionInfoRepository.save(transactionInfo);

			// 출금인 경우에만 평균 소비 정보 업데이트
			if (newAvgConsumptionAmount != null) {
				avgConsumptionAmountRepository.save(newAvgConsumptionAmount); // 평균 소비 정보 업데이트
			}

//			int newBalance = accountInfo.getBalance();

		} catch (BaseException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 1원 송금
	 *
	 * @param accountInfoSendCodeRequestDto
	 */
	@Override
	public void addTransaction(AccountInfoSendCodeRequestDto accountInfoSendCodeRequestDto) throws BaseException {
//		Optional<AccountInfo> byId = accountInfoRepository.findById(accountInfoSendCodeRequestDto.getAccountNumber());
		Optional<AccountInfo> byId = accountInfoRepository.findByAccountNumberBankCodeMemberId(accountInfoSendCodeRequestDto.getAccountNumber(), accountInfoSendCodeRequestDto.getBankCode());

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

	/**
	 * 계좌 거래내역을 조회하는 메소드입니다.
	 *
	 * @param transactionInfoListRequestDto
	 * @return
	 * @throws BaseException
	 */
	@Override
	public List<TransactionInfoListResponseDto> getTransactionInfoList(
		TransactionInfoListRequestDto transactionInfoListRequestDto) throws BaseException {
		Optional<AccountInfo> byId = accountInfoRepository.findById(transactionInfoListRequestDto.getAccountNumber());
		if (byId.isEmpty()) {
			throw new BaseException(BaseResponseStatus.ACCOUNT_NOT_FOUND);
		}
		return transactionInfoRepository.findByAccountInfo_AccountNumber(
			transactionInfoListRequestDto.getAccountNumber());
	}

	@Override
	public List<TransactionInfoTodayDto> getDailyTransactionInfoList(
		UUID memberId,
		TransactionInfoTodayListRequestDto transactionInfoTodayListRequestDto) throws BaseException {
		Timestamp lastRegDate = transactionInfoTodayListRequestDto.getLastRegDate();
		// Timestamp curDate = transactionInfoTodayListRequestDto.getCurDate();
		LocalDateTime currentDateTime = LocalDateTime.now();
		Timestamp curDate = Timestamp.valueOf(currentDateTime);
		log.debug("마지막 시간: " + lastRegDate + "현재 시간: " + curDate);
		List<TransactionInfoTodayDto> result = new ArrayList<>();
		// memberId에 해당하는 accountList 조회
		List<AccountInfo> byMemberId = accountInfoRepository.findByMember_Id(memberId);

		// accountList를 for문으로 돌면서 해당 구간 내 거래내역 리스트
		for (AccountInfo accountInfo : byMemberId) {
			log.debug("계좌 아이디 : " + accountInfo.getAccountNumber());
			Optional<List<TransactionInfo>> allByAccountNumber = transactionInfoRepository.findAllWithdrawalsByAccountNumber(
				accountInfo.getAccountNumber(), 2,
				lastRegDate, curDate);
			if (allByAccountNumber.isPresent()) {
				for (TransactionInfo transactionInfo : allByAccountNumber.get()) {
					log.debug("트랜잭션: " + transactionInfo);
					TransactionInfoTodayDto transactionInfoTodayDto = new TransactionInfoTodayDto(
						transactionInfo.getId(), transactionInfo.getCategory().getId(),
						transactionInfo.getEmotion().getEmotionScore(), transactionInfo.getTransactionName(),
						transactionInfo.getTransactionAmount(), transactionInfo.getTradeDate());
					log.debug("거래내역: " + transactionInfo);
					result.add(transactionInfoTodayDto);
				}
			}
		}
		result.sort((o1, o2) -> o1
				.getTradeDate().compareTo(o2.getTradeDate()));

		return result;
	}
}
