package org.msgr.watch.rest;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class WatchController {


    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public String ping(){
        return "Watch rest service is running.";
    }


}
