package kr.co.ipalab.lingobe.api.translation.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TranslationResponseDto {
    private Map<String, Object> score;
    private String translatedResult;
}