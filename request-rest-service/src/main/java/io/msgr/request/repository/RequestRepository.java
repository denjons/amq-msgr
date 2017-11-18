package io.msgr.request.repository;

import io.msgr.request.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository<Request, String> {

    
}
