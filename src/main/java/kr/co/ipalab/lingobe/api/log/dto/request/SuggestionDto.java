package kr.co.ipalab.lingobe.api.log.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionDto {
    @Schema(description = "추천 모델명", example = "llama2_7b")
    private String suggestionModel;

    @Schema(description = "타겟 단어", example = "세상")
    private Integer targetWord;

    @Schema(description = "문장 여부", example = "false")
    private Boolean sentence;

    @Schema(description = "컨텍스트 길이", example = "0")
    private Integer cntxtLen;

    @Schema(description = "문장을 사용할 경우 문장")
    private String text;

    @Schema(description = "약어 여부", example = "false")
    private Boolean abbreviation;

    @Schema(description = "추천 단어 리스트", example = "[\"지구\", \"세계\", \"세상\"]")
    private List<String> suggestionList;

    @Schema(description = "선택된 추천 단어", example = "세계")
    private String selectedSuggestion;
}
