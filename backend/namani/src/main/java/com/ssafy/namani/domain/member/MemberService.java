package com.ssafy.namani.domain.member;

import java.util.UUID;

import com.ssafy.namani.domain.member.dto.MemberRegisterRequestDto;
import com.ssafy.namani.global.response.BaseException;

public interface MemberService {

    void register(MemberRegisterRequestDto memberRegisterRequestDto) throws BaseException;

    void connectAccountWithMember(String accountNumber, UUID memberId) throws BaseException;
}
