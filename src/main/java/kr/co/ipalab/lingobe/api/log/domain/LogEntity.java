package kr.co.ipalab.lingobe.api.log.domain;

import kr.co.ipalab.lingobe.api.log.dto.request.LogRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "log")
public class LogEntity {
    @Id
    private String id;
    private LogRequestDto session;
}
