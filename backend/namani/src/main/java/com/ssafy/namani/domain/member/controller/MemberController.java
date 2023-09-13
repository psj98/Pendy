package com.ssafy.namani.domain.member.controller;


import com.ssafy.namani.domain.member.dto.request.MemberLoginRequestDto;
import com.ssafy.namani.domain.member.dto.response.MemberLoginResponseDto;
import com.ssafy.namani.domain.member.service.MemberService;
import com.ssafy.namani.domain.member.dto.request.MemberRegisterRequestDto;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final BaseResponseService baseResponseService;

    @PostMapping("/join")
    public ResponseEntity<BaseResponse<Object>> joinMember(@RequestBody MemberRegisterRequestDto memberRegisterRequestDto) {
        try {
            memberService.register(memberRegisterRequestDto);
            return ResponseEntity.ok(baseResponseService.getSuccessResponse(BaseResponseStatus.SUCCESS));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(baseResponseService.getFailureResponse(BaseResponseStatus.DUPLICATE_MEMBER_EMAIL));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<Object>> login(@RequestBody MemberLoginRequestDto requestDto){

        try {
            MemberLoginResponseDto responseDto = memberService.login(requestDto);
            return ResponseEntity.ok(baseResponseService.getSuccessResponse(responseDto));
        } catch (BaseException e) {
            return ResponseEntity.badRequest().body(baseResponseService.getFailureResponse(e.getStatus()));
        }

    }
}
