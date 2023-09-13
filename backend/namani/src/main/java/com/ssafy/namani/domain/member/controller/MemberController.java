package com.ssafy.namani.domain.member.controller;


import com.ssafy.namani.domain.member.dto.request.MemberDuplicationCheckRequestDto;
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
import org.apache.coyote.Response;
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
        } catch (BaseException e) {
            return ResponseEntity.badRequest().body(baseResponseService.getFailureResponse(e.getStatus()));
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

    @PostMapping("/duplicate-check")
    public ResponseEntity<BaseResponse<Object>> checkDuplication(@RequestBody MemberDuplicationCheckRequestDto requestDto){
        try {
            boolean isDuplicate = memberService.checkDuplication(requestDto);
            return ResponseEntity.ok(baseResponseService.getSuccessResponse(isDuplicate));
        }catch (BaseException e){
            return ResponseEntity.badRequest().body(baseResponseService.getFailureResponse(e.getStatus()));
        }
    }
}
