package kr.co.ipalab.lingobe.global.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class FlaskServerManager {

    @Value("${flask.url}")
    private static String flaskUrl;

    public static <T> T getFlaskResponse(String path, HttpMethod methodType, Object flaskRequestDto, Class<?> flaskResponseDtoClass) throws Exception {
        RestTemplate restTemplate = createRestTemplateWithTimeouts();
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
        return (T) responseEntity.getBody();
    }

    private static RestTemplate createRestTemplateWithTimeouts() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(5))
            .build();
    }
}
