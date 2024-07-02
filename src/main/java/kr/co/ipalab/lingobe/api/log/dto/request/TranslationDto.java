package kr.co.ipalab.lingobe.api.log.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TranslationDto {
    @Schema(description = "번역 모델명", example = "deepl")
    private String translationModel;

    @Schema(description = "소스 텍스트", example = "Hello, World!")
    private String targetText;

    @Schema(description = "소스 언어", example = "en")
    private String sourceLan;

    @Schema(description = "타겟 언어", example = "ko")
    private String targetLan;

    @Schema(description = "번역된 텍스트", example = "안녕, 세계!")
    private String translatedText;

    @Schema(description = "번역된 텍스트의 성능 평가 결과")
    private EvaluationDto evaluation;

    @Schema(description = "단어 추천 및 바꾼 여부")
    private List<SuggestionDto> suggestions;
}
