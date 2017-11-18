package io.msgr.agg.jms;

import com.google.gson.Gson;
import io.msgr.agg.App;
import io.msgr.agg.model.Request;
import io.msgr.agg.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class RequestReceiver {

    @Autowired
    RequestRepository repository;

    @JmsListener(destination = App.TOPIC, containerFactory = "myFactory")
    public void receiveMessage(String request) {
        System.out.println("received "+request);
        Gson gson = new Gson();
        repository.save(gson.fromJson(request, Request.class));
        System.out.println("Received <" + request + ">");
    }

}
