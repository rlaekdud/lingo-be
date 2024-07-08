package kr.co.ipalab.lingobe.api.suggestion.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kr.co.ipalab.lingobe.api.suggestion.dto.request.SuggestionRequestDto;
import kr.co.ipalab.lingobe.api.suggestion.dto.response.SuggestionResponseDto;
import kr.co.ipalab.lingobe.api.translation.dto.request.TranslationRequestDto;
import kr.co.ipalab.lingobe.api.translation.service.TranslationService;
import kr.co.ipalab.lingobe.flask.suggestion.dto.request.FlaskSuggestionRequestDto;
import kr.co.ipalab.lingobe.flask.suggestion.dto.response.FlaskSuggestionResponseDto;
import kr.co.ipalab.lingobe.flask.translation.dto.request.FlaskTranslationRequestDto;
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
    private final TranslationService translationService;

    /**
     * get suggestion word list from target word
     * @param suggestionRequestDto dto used in suggestionRequest
     *
     * @return SuggestionResponseDto
     */
    public SuggestionResponseDto getSuggestion(SuggestionRequestDto suggestionRequestDto) {
        try {
            // translate source language word to english word
            if (!suggestionRequestDto.getLan().equals("en")) {
                suggestionRequestDto.setTargetWord(translateWordToEng(suggestionRequestDto));

                // translate source language sentence to english sentence
                if (suggestionRequestDto.getSentence()) {
                    suggestionRequestDto.setText(translateSentenceToEng(suggestionRequestDto));
                }
            }
            log.info("suggestionRequestDto: {}", suggestionRequestDto.getTargetWord());

            // get suggestion word list from flask server
            FlaskSuggestionRequestDto flaskSuggestionRequest = new FlaskSuggestionRequestDto(suggestionRequestDto.getSuggestionModel(), suggestionRequestDto.getTargetWord(), suggestionRequestDto.getSentence(), suggestionRequestDto.getContextLen(), suggestionRequestDto.getText(), suggestionRequestDto.getAbbreviation());
            FlaskSuggestionResponseDto flaskSuggestionResponse = FlaskServerManager.getFlaskResponse(FLASK_SUGGESTION_PATH, HttpMethod.POST, flaskSuggestionRequest, FlaskSuggestionResponseDto.class);

            log.info("flaskSuggestionResponse: {}", flaskSuggestionResponse);

            // throw exception if flask server response is null
            if (flaskSuggestionResponse == null) {
                throw new FlaskResponseTimeoutError();
            }

            // translate english word to source language word
            List<String> translatedWordList = translateWordListFromEng(flaskSuggestionResponse, suggestionRequestDto);

            log.info("translatedWordList: {}", translatedWordList);

            // remove duplicated words
            translatedWordList = new ArrayList<>(new HashSet<>(translatedWordList));

            return SuggestionResponseDto.builder()
                .suggestions(translatedWordList)
                .build();

        } catch (Exception e) {
            throw new FlaskResponseTimeoutError();
        }
    }

    private String translateWordToEng(SuggestionRequestDto suggestionRequestDto) {

        TranslationRequestDto translationRequestDto = TranslationRequestDto.builder()
            .service(suggestionRequestDto.getTranslationModel())
            .query(suggestionRequestDto.getTargetWord())
            .sourceLan(suggestionRequestDto.getLan())
            .targetLan("en")
            .kwargs(new HashMap<>())
            .build();

        return translate(translationRequestDto);
    }

    private String translateSentenceToEng(SuggestionRequestDto suggestionRequestDto) {

        TranslationRequestDto translationRequestDto = TranslationRequestDto.builder()
            .service(suggestionRequestDto.getTranslationModel())
            .query(suggestionRequestDto.getText())
            .sourceLan(suggestionRequestDto.getLan())
            .targetLan("en")
            .build();

        return translate(translationRequestDto);
    }

    private List<String> translateWordListFromEng(FlaskSuggestionResponseDto flaskSuggestionResponseDto, SuggestionRequestDto suggestionRequestDto) {
        List<String> translatedWordList = new ArrayList<>();

        for (String word : flaskSuggestionResponseDto.getSuggestions()) {
            translatedWordList.add(translateWordFromEng(word, suggestionRequestDto));
        }

        return translatedWordList;
    }

    private String translateWordFromEng(String word, SuggestionRequestDto suggestionRequestDto) {

        TranslationRequestDto translationRequestDto = TranslationRequestDto.builder()
            .service(suggestionRequestDto.getTranslationModel())
            .query(word)
            .sourceLan("en")
            .targetLan(suggestionRequestDto.getLan())
            .kwargs(new HashMap<>())
            .build();

        return translate(translationRequestDto);
    }

    private String translate(TranslationRequestDto translationRequestDto) {
        return translationService.translateText(translationRequestDto).getTranslatedResult();
    }
}
