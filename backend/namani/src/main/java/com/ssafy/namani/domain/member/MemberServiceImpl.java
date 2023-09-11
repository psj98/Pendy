package com.ssafy.namani.domain.member;


import com.ssafy.namani.domain.accountInfo.AccountInfoRepository;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.member.dto.MemberRegisterRequestDto;
import com.ssafy.namani.domain.member.entity.Member;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private AccountInfoRepository accountInfoRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, AccountInfoRepository accountInfoRepository) {
        this.memberRepository = memberRepository;
        this.accountInfoRepository = accountInfoRepository;
    }


    /**
     * 회원 가입 API.
     * BCryptPasswordEncoder 이용한 Password 암호화 진행. 나머지는 JPA를 사용하여 저장.
     * @param memberRegisterRequestDto
     */

    @Override
    public void register(MemberRegisterRequestDto memberRegisterRequestDto) {

            Member member = new Member();
            log.info("call the register Service");

            member.setId(UUID.randomUUID());
            member.setEmail(memberRegisterRequestDto.getEmail());

            // Bcrypt를 이용한 Password 암호화
            member.setPassword(BCrypt.hashpw(memberRegisterRequestDto.getPassword(), BCrypt.gensalt()));

            member.setName(memberRegisterRequestDto.getName());
            member.setAge(memberRegisterRequestDto.getAge());
            member.setSalary(memberRegisterRequestDto.getSalary());

            memberRepository.save(member);

            for (String accountNumber : memberRegisterRequestDto.getAccounts()) {
                AccountInfo accountInfo = accountInfoRepository.findById(accountNumber).orElse(null);
                log.debug("memberAccoutns" + accountInfo.toString());
                if (accountInfo != null) {
                    accountInfo.updateMemberId(member);
                    accountInfoRepository.save(accountInfo);
                }
            }

    }

}
