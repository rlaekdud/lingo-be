package kr.co.ipalab.lingobe.api.translation.dto.request;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequestDto {
    private String service;
    private String query;
    private String sourceLan;
    private String targetLan;
    private Map<String, String> kargs;
}
