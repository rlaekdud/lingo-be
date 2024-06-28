package kr.co.ipalab.lingobe.api.translation.service;

import kr.co.ipalab.lingobe.api.translation.dto.request.TranslationRequestDto;
import kr.co.ipalab.lingobe.api.translation.dto.response.TranslationResponseDto;
import kr.co.ipalab.lingobe.flask.translation.dto.response.FlaskTranslationResponseDto;
import kr.co.ipalab.lingobe.global.exception.FlaskResponseTimeoutError;
import kr.co.ipalab.lingobe.global.utils.FlaskServerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * translation service class
 * @version 1.0.0
 * @author Kim Dayeong
 */
@Service
@RequiredArgsConstructor
public class TranslationService {

    private final String FLASK_TRANSLATION_PATH = "/translate";

    /**
     * get translation and its score from text
     * @param translationRequestDto translationRequest 시 사용하는 dto
     * @return Response<?>
     */
    public TranslationResponseDto translateText(TranslationRequestDto translationRequestDto) {
        try {
            FlaskTranslationResponseDto flaskTranslationResponse = FlaskServerManager.getFlaskResponse(FLASK_TRANSLATION_PATH, HttpMethod.POST, translationRequestDto, FlaskTranslationResponseDto.class);
            if (flaskTranslationResponse != null) {
                return TranslationResponseDto.builder()
                    .score(flaskTranslationResponse.getScore())
                    .translatedResult(flaskTranslationResponse.getOutput())
                    .build();
            } else {
                throw new FlaskResponseTimeoutError();
            }
        } catch (Exception e) {
            throw new FlaskResponseTimeoutError();
        }
    }

}
