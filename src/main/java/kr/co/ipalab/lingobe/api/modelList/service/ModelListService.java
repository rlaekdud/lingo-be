package kr.co.ipalab.lingobe.api.modelList.service;

import kr.co.ipalab.lingobe.api.modelList.dto.response.ModelListResponseDto;
import kr.co.ipalab.lingobe.global.exception.FlaskResponseTimeoutError;
import kr.co.ipalab.lingobe.global.utils.FlaskServerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * get model list service class
 * @version 1.0.0
 * @author Kim Dayeong
 */
@Service
@RequiredArgsConstructor
public class ModelListService {

    private final String FLASK_MODEL_LIST_PATH = "/model-list";

    /**
    * Get model list from flask server
    * @return model list
    */
    public ModelListResponseDto getModelList() {

        try {
            ModelListResponseDto flaskModelListResponse = FlaskServerManager.getFlaskResponse(FLASK_MODEL_LIST_PATH, HttpMethod.GET, null, ModelListResponseDto.class);
            return flaskModelListResponse;
        } catch (Exception e) {
            throw new FlaskResponseTimeoutError();
        }
    }
}
