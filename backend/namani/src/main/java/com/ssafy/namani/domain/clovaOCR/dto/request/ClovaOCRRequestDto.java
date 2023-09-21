package com.ssafy.namani.domain.clovaOCR.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ClovaOCRRequestDto {

    private MultipartFile image;

}
