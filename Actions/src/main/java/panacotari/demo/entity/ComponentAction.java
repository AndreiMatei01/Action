package panacotari.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import panacotari.demo.service.ServiceAction;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ComponentAction {

    @Autowired
    ServiceAction serviceAction;

    @Scheduled(cron = "50 * * * * ?")
    public void scheduledInsertInDataBase() throws ParseException, InterruptedException {
        System.out.println();
        serviceAction.insertInDatabase();
    }

}
