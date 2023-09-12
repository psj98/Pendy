package com.ssafy.namani.domain.member;

import java.util.UUID;

import com.ssafy.namani.domain.member.dto.MemberRegisterRequestDto;

public interface MemberService {
	void register(MemberRegisterRequestDto memberRegisterRequestDto);

	void connectAccountWithMember(String accountNumber, UUID memberId);
}
