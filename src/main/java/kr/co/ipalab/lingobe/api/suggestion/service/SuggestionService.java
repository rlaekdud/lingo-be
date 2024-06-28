package kr.co.ipalab.lingobe.api.suggestion.service;

import kr.co.ipalab.lingobe.api.suggestion.dto.request.SuggestionRequestDto;
import kr.co.ipalab.lingobe.api.suggestion.dto.response.SuggestionResponseDto;
import kr.co.ipalab.lingobe.flask.suggestion.dto.request.FlaskSuggestionRequestDto;
import kr.co.ipalab.lingobe.flask.suggestion.dto.response.FlaskSuggestionResponseDto;
import kr.co.ipalab.lingobe.global.exception.FlaskResponseTimeoutError;
import kr.co.ipalab.lingobe.global.utils.FlaskServerManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * suggestion service class
 * @version 1.0.0
 * @author Kim Dayeong
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SuggestionService {

    private final String FLASK_SUGGESTION_PATH = "/suggestion";

    /**
     * get suggestion word list from target word
     * @param suggestionRequestDto dto used in suggestionRequest
     *
     * @return SuggestionResponseDto
     */
    public SuggestionResponseDto getSuggestion(SuggestionRequestDto suggestionRequestDto) {
        try {
            log.info("SuggestionService.getSuggestion : suggestionRequestDto = {}", suggestionRequestDto);
            FlaskSuggestionRequestDto flaskSuggestionRequest = new FlaskSuggestionRequestDto(suggestionRequestDto.getModel(), suggestionRequestDto.getTargetWord(), suggestionRequestDto.getSentence(), suggestionRequestDto.getContextLen(), suggestionRequestDto.getText(), suggestionRequestDto.getAbbreviation());
            FlaskSuggestionResponseDto flaskSuggestionResponse = FlaskServerManager.getFlaskResponse(FLASK_SUGGESTION_PATH, HttpMethod.POST, flaskSuggestionRequest, FlaskSuggestionResponseDto.class);
            if (flaskSuggestionResponse != null) {
                return SuggestionResponseDto.builder()
                    .suggestions(
                        flaskSuggestionResponse.getSuggestions()
                    )
                    .build();
            } else {
                throw new FlaskResponseTimeoutError();
            }
        } catch (Exception e) {
            throw new FlaskResponseTimeoutError();
        }
    }
}
