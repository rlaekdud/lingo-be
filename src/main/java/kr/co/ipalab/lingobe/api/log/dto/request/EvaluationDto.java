package kr.co.ipalab.lingobe.api.log.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {
    private String job;
    private Double grade;
    private String feedback;
}
