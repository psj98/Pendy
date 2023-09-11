package com.ssafy.namani.global.response;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    // -------- 성공 코드 시작 -------- //
    SUCCESS(true, 1000, "요청에 성공했습니다."),
    // -------- 성공 코드 종료 -------- //

    // -------- 실패 코드 시작 -------- //
    /**
     * Member
     * Code : 2000번대
     */
    // ex) NOT_FOUND_MEMBER(false, 2001, "일치하는 사용자가 없습니다."), ...

    /**
     *  AccountInfo
     *  Code : 3000번대
     */
    CONFLICK_ACCOUNT_NUMBER(false, 3001, "이미 등록된 계좌번호입니다.");

    /**
     * TransactionInfo
     * Code : 4000번대
     */

    /**
     * Diary
     * Code : 5000번대
     */

    /**
     * Goal
     * Code : 6000번대
     */

    /**
     * DailyStatistic
     * Code : 7000번대
     */

    /**
     * MonthlyStatistic
     * Code : 8000번대
     */

    // 필요하면 추가할 것

    // -------- 실패 코드 종료 -------- //


    private boolean isSuccess; // 성공 여부
    private String message; // 메시지
    private int code; // 코드

    /**
     * BaseResponseStatus 에서 해당하는 코드를 매핑
     *
     * @param isSuccess
     * @param code
     * @param message
     */
    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
