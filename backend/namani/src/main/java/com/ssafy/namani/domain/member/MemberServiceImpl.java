package com.ssafy.namani.domain.member;

import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.accountInfo.repository.AccountInfoRepository;
import com.ssafy.namani.domain.ageSalary.entity.AgeSalary;
import com.ssafy.namani.domain.ageSalary.repository.AgeSalaryRepository;
import com.ssafy.namani.domain.member.dto.MemberRegisterRequestDto;
import com.ssafy.namani.domain.member.entity.Member;

import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AccountInfoRepository accountInfoRepository;
    private final AgeSalaryRepository ageSalaryRepository;

    /**
     * 회원 가입 API.
     * BCryptPasswordEncoder 이용한 Password 암호화 진행. 나머지는 JPA를 사용하여 저장.
     *
     * @param memberRegisterRequestDto
     */
    @Override
    public void register(MemberRegisterRequestDto memberRegisterRequestDto) throws BaseException {
        /* ----------- 이메일 중복 체크할 것 ----------- */

        Member member = Member.builder()
                .id(UUID.randomUUID())
                .email(memberRegisterRequestDto.getEmail())
                .password(BCrypt.hashpw(memberRegisterRequestDto.getPassword(), BCrypt.gensalt())) // BCrypt를 이용한 Password 암호화
                .name(memberRegisterRequestDto.getName())
                .age(memberRegisterRequestDto.getAge())
                .salary(memberRegisterRequestDto.getSalary())
                .build();

        memberRepository.save(member);

        /* 나이-소득 구간 테이블에 인원 증가 */
        // 연령대, 연봉대 계산
        Integer age = memberRegisterRequestDto.getAge() / 10 * 10; // 연령대
        Integer salary = memberRegisterRequestDto.getSalary() / 1000 * 1000; // 연봉대

        Optional<AgeSalary> ageSalaryOptional = ageSalaryRepository.getAgeSalaryInfo(age, salary);

        // 나이-소득 구간 정보 존재 체크
        if (!ageSalaryOptional.isPresent()) {
            throw new BaseException(BaseResponseStatus.NO_AGE_SALARY_INFO_BY_AGE_SALARY);
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
}

