package com.ssafy.namani.domain.s3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data
public class S3ResponseDto {

    private String accessUrl;
    private String originalName;
    private String storedName;
    // extension = 확장자
    private String extensionName;

    public S3ResponseDto(String originalName){
        this.originalName = originalName;
        this.extensionName = extractExtension(originalName);
        this.storedName = getFileName(originalName);
        this.accessUrl = "";
    }

    // 이미지 파일 확장자 추출 메소드
    public String extractExtension(String originalName){
        int index = originalName.lastIndexOf('.');

        return originalName.substring(index, originalName.length());
    }

    public void setAccessUrl(String accessUrl){
        this.accessUrl = accessUrl;
    }

    // 이미지 파일 이름 저장 위한 이름 변환 메소드
    public String getFileName(String originalName){
        return UUID.randomUUID()+"."+extractExtension(originalName);
    }

}
