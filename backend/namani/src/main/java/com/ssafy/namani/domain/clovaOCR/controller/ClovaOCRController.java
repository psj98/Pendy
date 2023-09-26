//package com.ssafy.namani.domain.clovaOCR.controller;
//
////import com.ssafy.namani.domain.clovaOCR.dto.request.ClovaOCRRequestDto;
//import com.ssafy.namani.domain.clovaOCR.dto.response.ClovaOCRResponseDto;
//import com.ssafy.namani.domain.clovaOCR.service.ClovaOCRService;
//import com.ssafy.namani.global.response.BaseException;
//import com.ssafy.namani.global.response.BaseResponse;
//import com.ssafy.namani.global.response.BaseResponseService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.sql.Time;
//import java.sql.Timestamp;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/clovas")
//public class ClovaOCRController {
//
//    private final ClovaOCRService clovaOCRService;
//    private final BaseResponseService baseResponseService;
//
////    @PostMapping("/inference")
////    public BaseResponse<Object> inferenceImage (@RequestBody MultipartFile requestDto){
////
////        try {
////            // String 에서 ResponseDto 로 변경해야함
////            String responseDto = clovaOCRService.execute(requestDto);
////            return baseResponseService.getSuccessResponse(responseDto);
////
////        } catch (BaseException e) {
////            return baseResponseService.getFailureResponse(e.status);
////        }
////    }
//
//    @PostMapping("/parsecheck")
//    public ClovaOCRResponseDto parseCheck(){
//        ClovaOCRResponseDto responseDto = new ClovaOCRResponseDto();
//
//        responseDto.setDate("2023-07-15");
//        responseDto.setIsAfternoon("오후");
//        responseDto.setTime("8:16:14");
//
//        Timestamp tempTimestamp = Timestamp.valueOf((responseDto.getDate() + " " + responseDto.getTime()));
//        System.out.println(tempTimestamp);
//
//        long addMillis = 43_200_000; // 12 시간 (ms)
//        if(responseDto.getIsAfternoon().equals("오후")) {
//            tempTimestamp = new Timestamp(tempTimestamp.getTime() + addMillis);
//            System.out.println(tempTimestamp);
//        }
//
//        return null;
//    }
//
//}
