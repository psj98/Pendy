package com.ssafy.namani.domain.avgConsumptionAmount.controller;

import com.ssafy.namani.domain.avgConsumptionAmount.dto.request.AvgConsumptionAmountForThreeMonthRequestDto;
import com.ssafy.namani.domain.avgConsumptionAmount.dto.response.AvgConsumptionAmountDetailResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.dto.response.AvgConsumptionAmountForThreeMonthResponseDto;
import com.ssafy.namani.domain.avgConsumptionAmount.service.AvgConsumptionAmountService;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponse;
import com.ssafy.namani.global.response.BaseResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumptions")
public class AvgConsumptionAmountController {

    private final AvgConsumptionAmountService avgConsumptionAmountService;
    private final BaseResponseService baseResponseService;

    @GetMapping("/three-month")
    public BaseResponse<Object> getAvgConsumptionAmountForThreeMonth(@RequestBody AvgConsumptionAmountForThreeMonthRequestDto avgConsumptionAmountForThreeMonthRequestDto) {
        try {
            Integer age = avgConsumptionAmountForThreeMonthRequestDto.getAge();
            Integer salary = avgConsumptionAmountForThreeMonthRequestDto.getSalary();

            List<AvgConsumptionAmountForThreeMonthResponseDto> avgConsumptionAmountAvg = avgConsumptionAmountService.getAvgConsumptionAmountForThreeMonth(age, salary, Timestamp.valueOf(LocalDateTime.now()));
            return baseResponseService.getSuccessResponse(avgConsumptionAmountAvg);
        } catch (BaseException e) {
            return baseResponseService.getFailureResponse(e.status);
        }
    }
}
