package com.ssafy.namani.domain.transactionInfo.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.ageSalary.repository.AgeSalaryRepository;
import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import com.ssafy.namani.domain.avgConsumptionAmount.repository.AvgConsumptionAmountRepository;
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
    private final AgeSalaryRepository ageSalaryRepository;
    private final AvgConsumptionAmountRepository avgConsumptionAmountRepository;

    @Autowired
    public TransactionInfoServiceImpl(TransactionInfoRepository transactionInfoRepository,
                                      AccountInfoRepository accountInfoRepository,
                                      CategoryRepository categoryRepository, AgeSalaryRepository ageSalaryRepository, AvgConsumptionAmountRepository avgConsumptionAmountRepository) {
        this.transactionInfoRepository = transactionInfoRepository;
        this.accountInfoRepository = accountInfoRepository;
        this.categoryRepository = categoryRepository;
        this.ageSalaryRepository = ageSalaryRepository;
        this.avgConsumptionAmountRepository = avgConsumptionAmountRepository;
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

        Integer age = accountInfo.getMember().getAge() / 10 * 10;
        Integer salary = accountInfo.getMember().getSalary() / 1000 * 1000;

        // 연령대, 연봉대에 해당하는 정보 조회
        Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age, salary);

        // 연령대, 연봉대에 해당하는 정보 없음
        if (!ageSalaryOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
        }

        AgeSalary ageSalary = ageSalaryOptional.get();
        AvgConsumptionAmount newAvgConsumptionAmount = null;

        // 출금인 경우
        if (transactionInfoRegistRequestDto.getCategoryId() != null) {
            Optional<Category> category = categoryRepository.findById(transactionInfoRegistRequestDto.getCategoryId());
            transactionInfo = TransactionInfo.builder()
                    .accountInfo(accountInfo)
                    .category(category.get())
                    .transactionName(transactionInfoRegistRequestDto.getTransactionName())
                    .transactionAmount(transactionInfoRegistRequestDto.getTransactionAmount())
                    .transactionType(transactionInfoRegistRequestDto.getTransactionType())
                    .afterBalance(accountInfo.getBalance() - transactionInfoRegistRequestDto.getTransactionAmount())
                    .build();

            // 나이-소득 구간, 카테고리, 현재 연월에 해당하는 평균 소비 정보 조회
            Optional<AvgConsumptionAmount> avgConsumptionAmountOptional = avgConsumptionAmountRepository.findByAgeSalaryIdCategoryId(ageSalary.getId(), category.get().getId(), Timestamp.valueOf(LocalDateTime.now()));

            // 나이-소득 구간, 카테고리, 현재 연월에 해당하는 평균 소비 정보 없음
            if (!avgConsumptionAmountOptional.isPresent()) {
                throw new BaseException(BaseResponseStatus.NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY_ID_AND_CATEGORY_ID_AND_REG_DATE);
            }

            // 평균 소비 정보 총합 수정
            AvgConsumptionAmount avgConsumptionAmount = avgConsumptionAmountOptional.get();
            newAvgConsumptionAmount = AvgConsumptionAmount.builder()
                    .id(avgConsumptionAmount.getId())
                    .ageSalary(avgConsumptionAmount.getAgeSalary())
                    .category(avgConsumptionAmount.getCategory())
                    .sumAmount(avgConsumptionAmount.getSumAmount() + transactionInfoRegistRequestDto.getTransactionAmount())
                    .regDate(avgConsumptionAmount.getRegDate())
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

        // 출금인 경우에만 평균 소비 정보 업데이트
        if (newAvgConsumptionAmount != null) {
            avgConsumptionAmountRepository.save(newAvgConsumptionAmount); // 평균 소비 정보 업데이트
        }

        int newBalance = accountInfo.getBalance();

        return new TransactionInfoRegistResponseDto(Integer.toString(newBalance));
    }

    /**
     * 1원 송금
     *
     * @param accountInfoSendCodeRequestDto
     */
    @Override
    public void addTransaction(AccountInfoSendCodeRequestDto accountInfoSendCodeRequestDto) throws BaseException {
        Optional<AccountInfo> byId = accountInfoRepository.findById(accountInfoSendCodeRequestDto.getAccountNumber());
        // 거래내역을 추가하려는 계좌번호가 있는지 확인
        if (byId.isPresent()) {
            // 랜덤3자리 숫자 발급
            int randomNum = (int) (Math.random() * 899) + 100;
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
