package panacotari.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import panacotari.demo.service.ServiceAction;

import java.text.ParseException;

@RestController
public class TestController {
    @Autowired
    ServiceAction serviceAction;

    @RequestMapping("/test")
    public void getInfo() throws InterruptedException, ParseException {
        serviceAction.insertInDatabase();
    }




}

