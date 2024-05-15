package kr.co.ipalab.lingobe.api.log.repository;

import kr.co.ipalab.lingobe.api.log.domain.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogEntity, String> {
}
