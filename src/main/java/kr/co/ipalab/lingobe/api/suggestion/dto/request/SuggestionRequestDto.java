package kr.co.ipalab.lingobe.api.suggestion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionRequestDto {
    private String model;
    private String targetWord;
    private Boolean sentence;
    private Integer contextLen;
    private String text;
    private Boolean abbreviation;
}
