package panacotari.demo.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import panacotari.demo.service.ServiceAction;

import java.text.ParseException;

@Component
public class Start {
//    @Autowired
//   ServiceAction serviceAction;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void doSomethingAfterStartup() throws ParseException, InterruptedException {
//        System.out.println("I have just started up");
//        serviceAction.insertInDatabase();
//    }
}
