package kr.co.ipalab.lingobe.flask.modelList.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelListResponseDto {
    private List<Model> modelList;
}
