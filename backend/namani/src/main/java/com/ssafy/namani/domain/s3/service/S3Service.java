package com.ssafy.namani.domain.s3.service;

import com.ssafy.namani.domain.s3.dto.response.S3ResponseDto;
import com.ssafy.namani.global.response.BaseException;
import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    S3ResponseDto saveImage(MultipartFile file) throws BaseException;
    void deleteImage(S3ResponseDto responseDto) throws BaseException;
}
