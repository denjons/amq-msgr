package io.msgr.request.producer;


import io.msgr.request.App;
import io.msgr.request.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestJmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendRequest(Request request) {
        jmsTemplate.convertAndSend(App.TOPIC, request);
    }

}
