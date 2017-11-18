package io.msgr.request;


import io.msgr.request.model.Request;
import io.msgr.request.model.Tag;
import io.msgr.request.reciever.RequestJmsReceiver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProducerServiceIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RequestJmsReceiver requestReceiver;

    ArrayList<String> messages;

    @Before
    public void setup() {
        messages = new ArrayList<String>();
    }

    @Test
    public void ping() {

        String body = testRestTemplate.getForObject("/ping", String.class);
        assertThat(body.contains("service is running")).isTrue();

    }

    @Test
    public void addRequestTest() throws InterruptedException {

        requestReceiver.setSubscriber(new RequestJmsReceiver.Subscriber() {
            @Override
            public void accept(Request request) {
                messages.add(request.getRequest());
            }
        });

        Request request;

        ArrayList<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("dennis"));
        for (int i = 0; i < 10; i++) {
            request = new Request();
            request.setRequest("some request " + i);
            request.setUserId(UUID.randomUUID().toString());
            request.setTags(tags);
            System.out.println("sending: " + request.toString());
            ResponseEntity<String> reponse = testRestTemplate.postForEntity("/add", request, String.class);
            System.out.println(" ---------- response: " + reponse.getStatusCode().value());
            assertEquals(200, reponse.getStatusCode().value());
        }

        Thread.sleep(1000);

        assertTrue(messages.size() > 0);

    }


    @Test
    public void addInvalidRequestTest() throws InterruptedException {

        requestReceiver.setSubscriber(new RequestJmsReceiver.Subscriber() {
            @Override
            public void accept(Request request) {
                messages.add(request.getRequest());
            }
        });

        ResponseEntity<String> reponse;

        Request request = new Request();
        request.setRequest("some bad request ");
        request.setUserId(UUID.randomUUID().toString());
        ArrayList<Tag> tags = new ArrayList<Tag>();
        reponse = testRestTemplate.postForEntity("/add", request, String.class);
        assertEquals(400, reponse.getStatusCode().value());

        Request request2 = new Request();
        request.setRequest("");
        request.setUserId(UUID.randomUUID().toString());
        tags = new ArrayList<Tag>();
        tags.add(new Tag("dennis"));
        request.setTags(tags);
        reponse = testRestTemplate.postForEntity("/add", request2, String.class);
        assertEquals(400, reponse.getStatusCode().value());

        Request request3 = new Request();
        request.setRequest("");
        tags = new ArrayList<Tag>();
        tags.add(new Tag("dennis"));
        request.setTags(tags);
        reponse = testRestTemplate.postForEntity("/add", request3, String.class);
        assertEquals(400, reponse.getStatusCode().value());

        Request request4 = new Request();
        request.setUserId(UUID.randomUUID().toString());
        request.setRequest("some request");
        reponse = testRestTemplate.postForEntity("/add", request3, String.class);
        assertEquals(400, reponse.getStatusCode().value());


        Thread.sleep(1000);

        assertEquals(0, messages.size());

    }

}
