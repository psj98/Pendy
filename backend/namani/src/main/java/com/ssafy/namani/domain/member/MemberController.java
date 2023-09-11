package com.ssafy.namani.domain.member;


import com.ssafy.namani.domain.member.dto.MemberRegisterRequestDto;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;
    private BaseResponseService baseResponseService;

    @Autowired
    public MemberController(MemberService memberService, BaseResponseService baseResponseService) {
        this.memberService = memberService;
        this.baseResponseService = baseResponseService;
    }

    @PostMapping("/join")
    public ResponseEntity<BaseResponse<Object>> joinMember(@RequestBody MemberRegisterRequestDto memberRegisterRequestDto) {
        log.info("call the joinController");
        try {
            memberService.register(memberRegisterRequestDto);
            return ResponseEntity.ok(baseResponseService.getSuccessResponse(BaseResponseStatus.SUCCESS));
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(baseResponseService.getFailureResponse(BaseResponseStatus.DUPLICATE_MEMBER_EMAIL));

        }
    }
}
