package com.ssafy.namani.domain.member.service;

import java.util.UUID;

import com.ssafy.namani.domain.member.dto.request.MemberDuplicationCheckRequestDto;
import com.ssafy.namani.domain.member.dto.request.MemberLoginRequestDto;
import com.ssafy.namani.domain.member.dto.request.MemberRegisterRequestDto;
import com.ssafy.namani.domain.member.dto.request.MemberUpdateRequestDto;
import com.ssafy.namani.domain.member.dto.response.MemberLoginResponseDto;
import com.ssafy.namani.global.response.BaseException;

public interface MemberService {

    void register(MemberRegisterRequestDto requestDto) throws BaseException;

    void connectAccountWithMember(String accountNumber, UUID memberId) throws BaseException;
    MemberLoginResponseDto login(MemberLoginRequestDto requestDto) throws BaseException;

    boolean checkDuplication(MemberDuplicationCheckRequestDto requestDto) throws BaseException;

    void updateMemberInfo(UUID memberId, MemberUpdateRequestDto requestDto) throws BaseException;


}
