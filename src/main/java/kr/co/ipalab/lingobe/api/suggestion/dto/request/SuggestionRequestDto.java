package kr.co.ipalab.lingobe.api.suggestion.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionRequestDto {

    @Schema(description = "모델명", example = "llama2_7b")
    private String model;

    @Schema(description = "타겟 단어", example = "CT")
    private String targetWord;

    @Schema(description = "문장 여부", example = "false")
    private Boolean sentence;

    @Schema(description = "컨텍스트 길이", example = "0")
    private Integer contextLen;

    @Schema(description = "문장을 사용할 경우 문장", example = "")
    private String text;

    @Schema(description = "약어 여부", example = "true")
    private Boolean abbreviation;
}
