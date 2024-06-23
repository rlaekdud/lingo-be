package kr.co.ipalab.lingobe.api.modelList.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    private List<String> translationModel;
    private List<String> suggestionModel;
}
