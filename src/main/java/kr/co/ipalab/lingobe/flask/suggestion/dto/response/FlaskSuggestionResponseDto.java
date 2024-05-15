package kr.co.ipalab.lingobe.flask.suggestion.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlaskSuggestionResponseDto {
    private List<String> suggestions;
}
