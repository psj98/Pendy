package com.ssafy.namani.domain.clovaOCR.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClovaOCRResponseDto {

    private String place;
    private int total;
    private String date;
//    private String isAfternoon;
    private String time;
    private Timestamp dateTime;

}
