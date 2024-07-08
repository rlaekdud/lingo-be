package kr.co.ipalab.lingobe.api.translation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranslationRequestDto {
    @Schema(description = "서비스", example = "huggingface_nllb")
    private String service;

    @Schema(description = "번역할 문장", example = "심장")
    private String query;

    @Schema(description = "소스 언어", example = "ko")
    private String sourceLan;

    @Schema(description = "타겟 언어", example = "en")
    private String targetLan;

    @Schema(description = "추가 옵션", example = "{}")
    private Map<String, String> kwargs;
}
