package com.ssafy.namani.domain.clovaOCR.service;

import com.ssafy.namani.domain.clovaOCR.dto.request.ClovaOCRRequestDto;
import com.ssafy.namani.global.response.BaseException;
import org.springframework.web.multipart.MultipartFile;

public interface ClovaOCRService {

    // String 에서 RepsonseDto 로 수정해야함
    String execute(MultipartFile request) throws BaseException;

}
