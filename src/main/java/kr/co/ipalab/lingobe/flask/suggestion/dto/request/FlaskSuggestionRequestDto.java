package kr.co.ipalab.lingobe.flask.suggestion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlaskSuggestionRequestDto {
    private String model;
    private Integer targetWord;
    private Boolean sentence;
    private Integer contextLen;
    private String text;
    private Boolean abbreviation;
}
