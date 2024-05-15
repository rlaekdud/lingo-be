package kr.co.ipalab.lingobe.api.log.dto.request;

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
    private String translationModel;
    private String targetText;
    private String sourceLan;
    private String targetLan;
    private String translatedText;
    private EvaluationDto evaluation;
    private List<SuggestionDto> suggestions;
}
