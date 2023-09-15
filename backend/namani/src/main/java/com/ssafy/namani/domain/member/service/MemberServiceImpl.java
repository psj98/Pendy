package com.ssafy.namani.domain.member.service;

import com.ssafy.namani.domain.accountInfo.dto.response.AccountListResponseDto;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.accountInfo.repository.AccountInfoRepository;
import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.ageSalary.repository.AgeSalaryRepository;
import com.ssafy.namani.domain.avgConsumptionAmount.service.AvgConsumptionAmountService;
import com.ssafy.namani.domain.jwt.dto.TokenDto;
import com.ssafy.namani.domain.jwt.service.JwtService;
import com.ssafy.namani.domain.member.dto.request.MemberDuplicationCheckRequestDto;
import com.ssafy.namani.domain.member.dto.request.MemberLoginRequestDto;
import com.ssafy.namani.domain.member.dto.request.MemberUpdateRequestDto;
import com.ssafy.namani.domain.member.dto.response.MemberLoginResponseDto;
import com.ssafy.namani.domain.member.repository.MemberRepository;
import com.ssafy.namani.domain.member.dto.request.MemberRegisterRequestDto;
import com.ssafy.namani.domain.member.entity.Member;

import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AccountInfoRepository accountInfoRepository;
    private final AgeSalaryRepository ageSalaryRepository;
    private final JwtService jwtService;
    private final AvgConsumptionAmountService avgConsumptionAmountService;


    /**
     * 회원 가입 API.
     * BCryptPasswordEncoder 이용한 Password 암호화 진행. 나머지는 JPA를 사용하여 저장.
     *
     * @param memberRegisterRequestDto
     */
    @Override
    public void register(MemberRegisterRequestDto memberRegisterRequestDto) throws BaseException {


        //중복확인 api로 뺌
        //PostMan 체크용 중복확인 메서드.
        //Front랑 엮을시 삭제.
        Optional<Member> validMemberEmail = memberRepository.findByEmail(memberRegisterRequestDto.getEmail());
        if (validMemberEmail.isPresent()) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_MEMBER_EMAIL);
        }

        //체크 후 회원가입.

        Member member = Member.builder()
                .id(UUID.randomUUID())
                .email(memberRegisterRequestDto.getEmail())
                .password(BCrypt.hashpw(memberRegisterRequestDto.getPassword(), BCrypt.gensalt())) // BCrypt를 이용한 Password 암호화
                .name(memberRegisterRequestDto.getName())
                .age(memberRegisterRequestDto.getAge())
                .salary(memberRegisterRequestDto.getSalary() * 10000)
                .build();

        memberRepository.save(member);

        /* 나이-소득 구간 테이블에 인원 증가 */
        // 연령대, 연봉대 계산
        Integer age = memberRegisterRequestDto.getAge() / 10 * 10; // 연령대
        Integer salary = memberRegisterRequestDto.getSalary() / 1000 * 1000; // 연봉대

        Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age, salary);

        // 나이-소득 구간 정보 존재 체크
        // To-do 연령대 새롭게 추가.
        if (!ageSalaryOptional.isPresent()) {
//            throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
            AgeSalary ageSalary = AgeSalary.builder()
                    .age(age)
                    .salary(salary)
                    .peopleNum(0)
                    .build();

            ageSalaryRepository.save(ageSalary);

            ageSalaryOptional = ageSalaryRepository.findByAgeSalary(age, salary);
        }

        // 인원수 계산
        AgeSalary ageSalary = ageSalaryOptional.get();
        Integer peopleNum = ageSalary.getPeopleNum() + 1; // 인원수

        AgeSalary newAgeSalary = AgeSalary.builder()
                .id(ageSalary.getId())
                .age(ageSalary.getAge())
                .salary(ageSalary.getSalary())
                .peopleNum(peopleNum)
                .build();

        ageSalaryRepository.save(newAgeSalary); // 나이-소득구간에 해당하는

        for (String accountNumber : memberRegisterRequestDto.getAccounts()) {
            AccountInfo accountInfo = accountInfoRepository.findById(accountNumber).orElse(null);
            if (accountInfo == null) {
                continue;
            }

            accountInfo.updateMemberId(member);
            accountInfoRepository.save(accountInfo);

            // 현재까지의 거래내역을 평균 소비 정보에 업데이트
            avgConsumptionAmountService.updateAvgConsumptionAmountByMemberJoin(accountNumber, age, salary);
        }
    }

    /**
     * 계좌 정보에 유저 id를 연결하는 메소드입니다.
     *
     * @param accountNumber
     * @param memberId
     */
    @Override
    public void connectAccountWithMember(String accountNumber, UUID memberId) {
        Optional<AccountInfo> accountInfo = accountInfoRepository.findById(accountNumber);
        Optional<Member> member = memberRepository.findById(memberId);

        if (accountInfo.isPresent() && member.isPresent()) {
            accountInfo.get().updateMemberId(member.get());
            accountInfoRepository.save(accountInfo.get());
        }
    }

    /**
     * 로그인 메소드 입니다.
     *
     * @param memberLoginRequestDto
     * @return
     * @throws BaseException
     */
    @Override
    public MemberLoginResponseDto login(MemberLoginRequestDto memberLoginRequestDto) throws BaseException {
        Optional<Member> memberOptional = memberRepository.findByEmail(memberLoginRequestDto.getEmail());

        if (!memberOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_MEMBER);
        }

        Member member = memberOptional.get();

        if (!BCrypt.checkpw(memberLoginRequestDto.getPassword(), member.getPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_MEMBER);
        }

        TokenDto tokenDto = jwtService.generateTokens(member.getId());


        List<AccountListResponseDto> accountList = new ArrayList<>();

        List<AccountInfo> optionalAccountInfos = accountInfoRepository.findByMember_Id(member.getId());


        List<AccountInfo> accountInfos = optionalAccountInfos;
        for (AccountInfo accountInfo : accountInfos) {
            AccountListResponseDto accountListResponseDto = AccountListResponseDto.builder()
                    .bankCode(accountInfo.getBank().getBankCode())
                    .accountNumber(accountInfo.getAccountNumber())
                    .build();

            accountList.add(accountListResponseDto);
        }


        MemberLoginResponseDto responseDto = MemberLoginResponseDto.builder()
                .accessToken(tokenDto.getAccessToken())
                .email(member.getEmail())
                .name(member.getName())
                .age(member.getAge())
                .salary(member.getSalary())
                .accountListResponseDtoList(accountList)
                .build();

        return responseDto;

    }

    @Override
    public boolean checkDuplication(MemberDuplicationCheckRequestDto requestDto) throws BaseException {
        Optional<Member> existingMember = memberRepository.findByEmail(requestDto.getEmail());

        if (existingMember.isPresent()) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_MEMBER_EMAIL);
        }
        return true;
    }

    @Override
    public void updateMemberInfo(UUID memberId, MemberUpdateRequestDto requestDto) throws BaseException {
        Member member = memberRepository.findById(memberId).get();

        Member member1 = member.toBuilder()
                .age(requestDto.getAge())
                .password(BCrypt.hashpw(requestDto.getPassword(), BCrypt.gensalt()))
                .salary(requestDto.getSalary()).
                build();

        memberRepository.save(member1);

    }
}

