package kr.co.ipalab.lingobe.api.log.dao;

import kr.co.ipalab.lingobe.api.log.domain.LogEntity;
import kr.co.ipalab.lingobe.api.log.dto.request.LogRequestDto;
import kr.co.ipalab.lingobe.api.log.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * LogDao
 * @version 1.0.0
 * @author Kim Dayeong
 */
@Repository
@RequiredArgsConstructor
public class LogDao {

    private final LogRepository logRepository;

    /**
     * Save log with logRequestDto
     * @param logRequestDto logRequestDto
     */
    public void saveLog(LogRequestDto logRequestDto) {
        LogEntity logEntity = new LogEntity();
        logEntity.setSession(logRequestDto);
        logRepository.save(logEntity);
    }
}
