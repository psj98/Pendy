package com.ssafy.namani.domain.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssafy.namani.domain.s3.dto.response.S3ResponseDto;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private static String bucketName= "namani-bucket";

    private final AmazonS3Client amazonS3Client;

    @Override
//    @Transactional
    public S3ResponseDto saveImage(MultipartFile file) throws BaseException {
        String originalName = file.getOriginalFilename();
        S3ResponseDto responseDto = new S3ResponseDto(originalName);
        String filename = responseDto.getStoredName();

        try {
            /*
             * ObjectMetadata는 MultipartFile에서 제공하는 getInputStream() 메소드같은 것이 없어서
             * 객체를 생성하여 매개변수로 전달해주어야함
             * ObjectMetadata는 InputStream에 저장도니 파일의 정보, 즉 MultipartFile의 정보이기 때문에
             * 파일의 형식이 어떤지, 길이가 어느정도 되는지 정보 입력해야함
             * */
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getInputStream().available());

            amazonS3Client.putObject(bucketName, filename, file.getInputStream(), objectMetadata);

            // 이미지 접근 URL의 경우, amazonS3Client.getUrl() 메소드를 통해 이미지 접근 URL 얻음
            String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();
            responseDto.setAccessUrl(accessUrl);
        } catch(IOException e) {
            throw new BaseException(BaseResponseStatus.S3_FILE_IO_ERROR);
        }

        return responseDto;
    }

    @Override
    public void deleteImage(S3ResponseDto responseDto) throws BaseException {



    }
}
