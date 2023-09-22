package com.ssafy.namani.domain.clovaOCR.service;

import com.ssafy.namani.domain.clovaOCR.dto.request.ClovaOCRRequestDto;
import com.ssafy.namani.domain.s3.dto.response.S3ResponseDto;
import com.ssafy.namani.global.response.BaseException;
import org.springframework.web.multipart.MultipartFile;

public interface ClovaOCRService {

    // String 에서 RepsonseDto 로 수정해야함
    String execute(S3ResponseDto request) throws BaseException;

}
