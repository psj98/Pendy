package com.ssafy.namani.domain.avgConsumptionAmount.service;

import com.ssafy.namani.domain.avgConsumptionAmount.entity.AvgConsumptionAmount;
import com.ssafy.namani.global.response.BaseException;

import java.sql.Timestamp;

public interface AvgConsumptionAmountService {

    AvgConsumptionAmount getAvgConsumptionAmountInfo(Integer age, Integer salary, Timestamp regDate) throws BaseException;
}
