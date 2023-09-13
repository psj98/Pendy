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
	NOT_FOUND_MEMBER(false, 2001, "일치하는 사용자가 없습니다."),
	DUPLICATE_MEMBER_EMAIL(false, 2002, "이미 사용중인 이메일입니다."),
	INVALID_MEMBER_PASSWORD(false, 2003, "비밀번호가 일치하지 않습니다."),

	/**
	 *  AccountInfo
	 *  Code : 3000번대
	 */
	CONFLICK_ACCOUNT_NUMBER(false, 3001, "이미 등록된 계좌번호입니다."),
	INVALID_AUTHORIZATION_NUMBER(false, 3002, "잘못된 인증 코드입니다."),

	/**
	 * TransactionInfo
	 * Code : 4000번대
	 */
	ACCOUNT_NOT_FOUND(false, 4001, "존재하지 않는 계좌번호입니다"),
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

    /**
     * AvgConsumptionAmount, AgeSalary
     * Code : 9000번대
     */
    NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY_ID_AND_REG_DATE(false, 9001, "나이, 소득, 해당 연월에 해당하는 평균 소비 정보가 없습니다."),
    NO_AVG_CONSUMPTION_AMOUNT_BY_AGE_SALARY_ID_AND_CATEGORY_ID_AND_REG_DATE(false, 9002, "나이, 소득, 카테고리, 해당 연월에 해당하는 평균 소비 정보가 없습니다."),
	NO_AGE_SALARY_INFO_BY_AGE_SALARY(false, 9003, "나이, 소득 구간에 해당하는 정보가 없습니다.");

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
