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
    private String targetWord;
    private Boolean sentence;
    private Integer cntxt_len;
    private String text;
    private Boolean abbreviation;
}
