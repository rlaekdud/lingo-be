package kr.co.ipalab.lingobe.flask.translation.dto.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlaskTranslationResponseDto {
    private Map<String, Object> score;
    private String query; // result of translation
}
