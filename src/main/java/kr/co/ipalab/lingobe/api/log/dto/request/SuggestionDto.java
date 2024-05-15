package kr.co.ipalab.lingobe.api.log.dto.request;

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
    private String suggestionModel;
    private Integer targetWord;
    private Boolean sentence;
    private Integer cntxtLen;
    private String text;
    private Boolean abbreviation;
    private List<String> suggestionList;
    private String selectedSuggestion;
}
