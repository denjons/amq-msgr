package org.msgr.watch.jms;

import org.msgr.watch.App;
import org.springframework.jms.annotation.JmsListener;

public class TagRequestSetJmsReciever {


    @JmsListener(destination = App.TOPIC, containerFactory = "myFactory")
    public void recieveTagReqiestSet(){

    }
}
