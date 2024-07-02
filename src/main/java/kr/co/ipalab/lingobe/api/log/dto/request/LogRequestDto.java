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
public class LogRequestDto {
    @Schema(description = "세션(각 번역 결과의 집합)")
    private List<TranslationDto> session;
}
