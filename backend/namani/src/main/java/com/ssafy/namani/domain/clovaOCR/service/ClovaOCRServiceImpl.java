package com.ssafy.namani.domain.clovaOCR.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
//import com.ssafy.namani.domain.clovaOCR.dto.request.ClovaOCRRequestDto;
import com.ssafy.namani.domain.clovaOCR.dto.response.ClovaOCRResponseDto;
import com.ssafy.namani.domain.s3.dto.response.S3ResponseDto;
import com.ssafy.namani.global.response.BaseException;
import com.ssafy.namani.global.response.BaseResponseService;
import com.ssafy.namani.global.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClovaOCRServiceImpl implements ClovaOCRService {
    // custom api
    public static String CLOVA_SECRET = "SnBveHB4WFlMSmtUeHZNSmJablVtR1VpTFlRREFFdEE=";
    public static String CLOVA_API_URL = "https://v5cw7ku89t.apigw.ntruss.com/custom/v1/25124/1f41da20bfd452e64f995a675c04846e86ca91378e358cef9c819761f3cefc62/infer";

    // document api ( 영수증 특화 )
//    public static String CLOVA_SECRET = "U3BLT2FlS0JQTWZvVnZNZ3RySGhxUmNIUmZVRkVrSW4=";
//    public static String CLOVA_API_URL = "https://t3ky57orz4.apigw.ntruss.com/custom/v1/25207/d94e1f9c46f6ca3d0bdb16db4e84f44457a8ae9e945ebc22624cdf246dda5f04/document/receipt";

    /**
     * CLOVA OCR에 요청 및 수신
     * @param request
     * @return String result
     */
    public ClovaOCRResponseDto execute(S3ResponseDto request){
        try{
            // total time check start
            StopWatch totalTime = new StopWatch();
            totalTime.start();

            // Request Send
            StopWatch requestTime = new StopWatch();
            requestTime.start();

            URL url = new URL(CLOVA_API_URL);
            HttpURLConnection connection = createRequestHeader(url);
            createRequestBody(connection, request);
            requestTime.stop();
            System.out.println("request 생성시간 : "+ requestTime.getTotalTimeMillis()+ "ms");

            // Get Response
            StopWatch responseTime = new StopWatch();
            responseTime.start();

            StringBuilder response = getResponseData(connection);
            responseTime.stop();
            System.out.println("response 수신시간 : "+ responseTime.getTotalTimeMillis()+"ms");

            // Parse Data
            StopWatch parseTime = new StopWatch();
            parseTime.start();

            ClovaOCRResponseDto result = parseResponseData(response);

            parseTime.stop();
            System.out.println("parse 시간 : " + parseTime.getTotalTimeMillis()+"ms");

            totalTime.stop();
            System.out.println("Total 시간 : "+ totalTime.getTotalTimeMillis()+ "ms");

//            System.out.println(result.toString());
            return result;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Clova OCR 요청 헤더 생성
     * @param url
     * @return
     * @throws IOException
     */
    private static HttpURLConnection createRequestHeader(URL url) throws IOException{
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setReadTimeout(5000);
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("X-OCR-SECRET",CLOVA_SECRET);
        return connection;
    }

    /**
     * Clova OCR 요청 바디 생성 후 요청
     * @param connection
     * @param request
     * @throws IOException
     */
    //"images": [{ "format": "string", "url": "string", "data": "string", "name": "string", "templateIds": [ 0 ] }],
    private static void createRequestBody(HttpURLConnection connection, S3ResponseDto request) throws IOException{
        JSONObject image = new JSONObject();
        // 이미지 확장자랑 이름 추출해야함 , 현재는 임시로
        image.put("format","jpg");
        image.put("name",request.getOriginalName());
        // images.url 혹은 images.data 중 하나가 존재해야 함, URL은 이미지를 가져 올 수 있는 공개 URL이어야 함
        image.put("url",request.getAccessUrl());
            // Template OCR API에서는 이 필드를 설정하지 않으면 도메인에 배포된 모든 서비스 템플릿으로 자동 분류됨
//        JSONArray templateIds = new JSONArray();

        JSONArray images = new JSONArray();
        images.put(image);

        JSONObject requestObject = new JSONObject();
        requestObject.put("version","V2");
        requestObject.put("requestId", UUID.randomUUID().toString());
        requestObject.put("timestamp",System.currentTimeMillis());
        requestObject.put("lang","ko");
        requestObject.put("resultType","string");
        requestObject.put("images",images);

        connection.connect();

        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.write(requestObject.toString().getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }


    /**
     * 응답 데이터 받아서 return
     * @param connection
     * @return StringBuilder response
     * @throws IOException
     */
    private static StringBuilder getResponseData(HttpURLConnection connection) throws IOException {
        BufferedReader reader = checkResponse(connection);
        String line;
        StringBuilder response = new StringBuilder();
        while((line = reader.readLine()) != null){
            response.append(line);
        }

        reader.close();

        return response;
    }


    /**
     * Clova API 응답 확인
     * @param connection
     * @return BufferedReader reader
     * @throws IOException
     */
    private static BufferedReader checkResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        BufferedReader reader;

        if(responseCode == 200) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }else{
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        return reader;
    }


    /**
     * 데이터 간단히 파싱
     * @param response
     * @return StringBuilder result
     * @throws BaseException
     */
    private static ClovaOCRResponseDto parseResponseData(StringBuilder response) throws BaseException, ParseException {
        JSONParser responseParser = new JSONParser(response.toString());
        LinkedHashMap<String, String> hashMap = (LinkedHashMap<String, String>) responseParser.parse();
        JSONObject parsed = new JSONObject(hashMap);
        JSONArray parsedImages = (JSONArray) parsed.get("images");
        ClovaOCRResponseDto responseDto = new ClovaOCRResponseDto();

        if (parsedImages != null){
            JSONObject parsedImage = (JSONObject) parsedImages.get(0);
            JSONArray parsedTexts = (JSONArray) parsedImage.get("fields");

            // 필드별로 분류해서 DTO에 저장
            for(int i=0;i<parsedTexts.length();i++){
                JSONObject current = (JSONObject) parsedTexts.get(i);
                String text = ((String) current.get("inferText")).replaceAll(System.getProperty("line.separator"), "");
                text = text.replaceAll(" ","");

                // 장소와 시간은 공백 제거
                if(current.get("name").equals("place")){
                    responseDto.setPlace(text);
                }else if(current.get("name").equals("total")){
                    responseDto.setTotal(Integer.parseInt(text));
                }else if(current.get("name").equals("date")){
                    responseDto.setDate(text);
                }else if(current.get("name").equals("isafternoon")){
                    responseDto.setIsAfternoon(text);
                }else if(current.get("name").equals("time")){
                    responseDto.setTime(text);
                }
//                result.append((String) current.get("inferText")).append(" ");
            }

            Timestamp tempTimestamp = Timestamp.valueOf((responseDto.getDate()+" "+responseDto.getTime()));
            long addMillis = 43_200_200; // 12 시간 (ms)
            if(responseDto.getIsAfternoon().equals("오후")){
                tempTimestamp = new Timestamp(tempTimestamp.getTime()+addMillis);
            }
            responseDto.setDateTime(tempTimestamp);
        }

        return responseDto;
//        throw new BaseException(BaseResponseStatus.CLOVA_PARSING_ERROR);

    }

}
