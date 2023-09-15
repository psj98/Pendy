package com.ssafy.namani.domain.statistic.entity;

public interface IMonthlyStatisticAvg {

    Integer getCategoryId(); // 카테고리 아이디

    String getCategoryName(); // 카테고리 이름

    Integer getAmount(); // 총합
}
