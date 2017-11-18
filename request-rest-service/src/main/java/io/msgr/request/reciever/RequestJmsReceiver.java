package io.msgr.request.reciever;


import io.msgr.request.App;
import io.msgr.request.model.Request;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class RequestJmsReceiver {

    private Subscriber subscriber;

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @PostConstruct
    private void init() {
        subscriber = new Subscriber() {
            @Override
            public void accept(Request request) {
                System.out.println("Default subscriber received <" + request + ">");
            }

        };

    }

    @JmsListener(destination = App.TOPIC, containerFactory = "myFactory")
    public void receiveMessage(Request request) {

        System.out.println("Received <" + request + ">");
        subscriber.accept(request);
    }

    public interface Subscriber {
        void accept(Request request);
    }
}
