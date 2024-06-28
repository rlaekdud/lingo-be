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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FlaskServerManager {

    private static String flaskIp;
    private static String flaskPort;
    private static String flaskUri;

    @Value("${flask.ip}")
    public void setFlaskIp(String flaskIp) {
        FlaskServerManager.flaskIp = flaskIp;
    }

    @Value("${flask.port}")
    public void setFlaskPort(String flaskPort) {
        FlaskServerManager.flaskPort = flaskPort;
    }

    @Value("${flask.uri}")
    public void setFlaskUri(String flaskUri) {
        FlaskServerManager.flaskUri = flaskUri;
    }

    public static <T> T getFlaskResponse(String path, HttpMethod methodType, Object flaskRequestDto, Class<?> flaskResponseDtoClass) throws Exception {
        RestTemplate restTemplate = createRestTemplateWithTimeouts();
        ObjectMapper objectMapper = new ObjectMapper();

        // Header set
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "";
        // Body set
        if (flaskRequestDto != null) {
            requestBody = objectMapper.writeValueAsString(flaskRequestDto);
        }

        // HttpEntity set
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, httpHeaders);

        // request to flask server
        try {
            ResponseEntity<?> responseEntity = restTemplate.exchange(
                flaskIp + flaskPort + flaskUri + path,
                methodType,
                httpEntity,
                flaskResponseDtoClass
            );

            // response object return
            return (T) responseEntity.getBody();

        } catch (RestClientException e) {
            // process exception
            if (e.getMessage().contains("Read timed out")) {
                throw new Exception("Flask server response timeout");
            }
        }

        return null;
    }

    private static RestTemplate createRestTemplateWithTimeouts() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(10))
            .build();
    }
}
