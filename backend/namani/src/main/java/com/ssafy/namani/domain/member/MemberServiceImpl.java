package com.ssafy.namani.domain.member;


import com.ssafy.namani.domain.accountInfo.AccountInfoRepository;
import com.ssafy.namani.domain.accountInfo.entity.AccountInfo;
import com.ssafy.namani.domain.member.dto.MemberRegisterRequestDto;
import com.ssafy.namani.domain.member.entity.Member;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;
    private AccountInfoRepository accountInfoRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

        member.setEmail(memberRegisterRequestDto.getEmail());

        //Bcrypt를 이용한 Password 암호화
        String encodedPassword = passwordEncoder.encode(memberRegisterRequestDto.getPassword());
        member.setPassword(encodedPassword);

        member.setName(memberRegisterRequestDto.getName());
        member.setAge(memberRegisterRequestDto.getAge());
        member.setSalary(memberRegisterRequestDto.getSalary());

        //회원 정보 저장.
        memberRepository.save(member);

        //계좌 정보 연결 및 저장
        for(String accountNumber : memberRegisterRequestDto.getAccounts()){
            AccountInfo accountInfo = accountInfoRepository.findById(accountNumber).orElse(null);
            if(accountInfo != null){
                accountInfo.updateMemberId(member);
                accountInfoRepository.save(accountInfo);
            }
        }
    }
}
