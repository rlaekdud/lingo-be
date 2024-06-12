package kr.co.ipalab.lingobe.api.log.domain;

import java.util.List;
import kr.co.ipalab.lingobe.api.log.dto.request.TranslationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "log")
public class LogEntity {
    @Id
    private String id;
    private List<TranslationDto> session;
}
