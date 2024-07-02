package kr.co.ipalab.lingobe.api.log.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {
    @Schema(description = "직업", example = "computer scientist")
    private String job;

    @Schema(description = "평점", example = "5.0")
    private Double grade;

    @Schema(description = "피드백", example = "Good job!")
    private String feedback;
}
