package com.ssafy.namani.global.response;

import org.springframework.stereotype.Component;

@Component
public class BaseResponseService {

    /**
     * 성공 응답 메서드
     *
     * @param data - 결과 데이터
     * @return BaseResponse - 응답 객체
     */
    public <T> BaseResponse<Object> getSuccessResponse(T data) {
        return BaseResponse.builder()
                .isSuccess(true)
                .code(BaseResponseStatus.SUCCESS.getCode())
                .message(BaseResponseStatus.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    /**
     * 실패 응답 메서드
     *
     * @param status - BaseResponseStatus에서 생성한 status
     * @return BaseResponse - 응답 객체
     */
    public <T> BaseResponse<Object> getFailureResponse(BaseResponseStatus status) {
        return BaseResponse.builder()
                .isSuccess(status.isSuccess())
                .code(status.getCode())
                .message(status.getMessage())
                .build();
    }
}
