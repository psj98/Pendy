package com.ssafy.namani.domain.member;

import com.ssafy.namani.domain.member.dto.MemberRegisterRequestDto;

public interface MemberService {
    void register(MemberRegisterRequestDto memberRegisterRequestDto);

}
