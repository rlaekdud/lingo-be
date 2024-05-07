package kr.co.ipalab.lingobe.global.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FlaskController {

    @Value("${flask.url}")
    private String flaskUrl;

    public Object getFlaskResponse(String path, HttpMethod methodType, Object flaskRequestDto, Class<?> flaskResponseDtoClass) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        // Body set
        String requestBody = objectMapper.writeValueAsString(flaskRequestDto);

        // HttpEntity set
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, httpHeaders);

        // request to flask server
        ResponseEntity<?> responseEntity = restTemplate.exchange(
            flaskUrl + path,
                methodType,
                httpEntity,
                flaskResponseDtoClass
        );

        // response object return
        return responseEntity.getBody();
    }
}
