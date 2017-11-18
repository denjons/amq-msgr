package io.msgr.request;


import io.msgr.request.model.Request;
import io.msgr.request.model.Tag;
import io.msgr.request.producer.RequestJmsProducer;
import io.msgr.request.reciever.RequestJmsReceiver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestProducerTest {

    @Autowired
    RequestJmsProducer requestProducer;

    @Autowired
    RequestJmsReceiver requestReceiver;

    ArrayList<String> messages;
    UUID uuid;

    @Before
    public void setup() {
        messages = new ArrayList<String>();
    }

    @Test
    public void senRequestTest() throws InterruptedException {

        requestReceiver.setSubscriber(new RequestJmsReceiver.Subscriber() {
            @Override
            public void accept(Request request) {
                messages.add(request.getRequest());
            }
        });

        Request request = new Request();
        for (int i = 0; i < 10; i++) {
            request.setRequest("some request " + i);
            request.setUserId(UUID.randomUUID().toString());
            ArrayList<Tag> tags = new ArrayList<Tag>();
            tags.add(new Tag("dennis"));
            request.setTags(tags);
            requestProducer.sendRequest(request);
        }

        System.out.println("waiting one second");
        Thread.sleep(1000);

        assertEquals(10, messages.size());

    }
}
