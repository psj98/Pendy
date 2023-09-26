package com.ssafy.namani.domain.clovaOCR.dto.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ClovaOCRResponseDto {

    private String place;
    private int total;
    private String date;
    private String isAfternoon;
    private String time;
    private Timestamp dateTime;

}
