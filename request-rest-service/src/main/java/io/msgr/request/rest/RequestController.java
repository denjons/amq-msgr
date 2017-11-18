package io.msgr.request.rest;

import io.msgr.request.model.Request;
import io.msgr.request.producer.RequestJmsProducer;
import io.msgr.request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RequestController {

    @Autowired
    RequestJmsProducer requestProducer;

    @Autowired
    RequestRepository requestRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/ping")
    public String ping() {
        return "request service is running";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add", consumes = "application/json")
    public ResponseEntity<?> addRequest(@Valid @RequestBody Request request, Errors error) {

        if (error.hasErrors() || request == null) {
            error.getAllErrors().stream().forEach(System.out::println);
            return ResponseEntity.badRequest().body("invalid request");
        }

        System.out.println("got request : " + request.getRequest());

        requestRepository.insert(request);
        requestProducer.sendRequest(request);

        return ResponseEntity.ok("added request");
    }
}
