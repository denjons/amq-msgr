package io.msgr.agg.repository;

import io.msgr.agg.model.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, String> {

}
