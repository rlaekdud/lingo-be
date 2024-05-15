package kr.co.ipalab.lingobe.api.log.service;

import com.mongodb.MongoException;
import kr.co.ipalab.lingobe.api.log.dao.LogDao;
import kr.co.ipalab.lingobe.api.log.dto.request.LogRequestDto;
import kr.co.ipalab.lingobe.global.exception.DatabaseAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * log service class
 * @version 1.0.0
 * @author Kim Dayeong
 */
@Service
@RequiredArgsConstructor
public class LogService {

    private final LogDao logDao;

    /**
     * Save log from request
     * @param logRequestDto logRequestDto
     */
    public void saveLogFromRequest(LogRequestDto logRequestDto) {
        try {
            logDao.saveLog(logRequestDto);
        } catch (MongoException e) {
            throw new DatabaseAccessException();
        }
    }

}
