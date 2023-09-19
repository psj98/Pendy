package com.ssafy.namani.domain.transactionInfo.dto.response;

import java.util.Date;

import lombok.Getter;

@Getter
public class TransactionInfoListResponseDto {
    private Integer categoryId; // 카테고리 아이디
    private String categoryName; // 카테고리 이름
    private String name;
    private Integer transactionAmount;
    private Integer transactionType;
    private Integer afterBalance;
    private Date tradeDate;

    public TransactionInfoListResponseDto(Integer categoryId, String categoryName, String name,
                                          Integer transactionAmount, Integer transactionType, Integer afterBalance, Date tradeDate) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.name = name;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.afterBalance = afterBalance;
        this.tradeDate = tradeDate;
    }
}