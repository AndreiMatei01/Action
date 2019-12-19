package panacotari.demo.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import panacotari.demo.entity.Action;
import panacotari.demo.repositoy.RepositoryAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceAction {

    @Autowired
    private WebDriver webDriver;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RepositoryAction repositoryAction;

    public void insertInDatabase() throws InterruptedException, ParseException {
        List<Action> listWithAllActions=new ArrayList<>();
        List<String> listWithFirstFive = getTopFive();
        for (String currentActionName: listWithFirstFive) {
            listWithAllActions.addAll(getListWithPreaparedActionForDatabase(currentActionName));
        }
        repositoryAction.insertInDataBase(listWithAllActions);
    }


    private List<String> getTopFive() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aimatei\\Downloads\\chromCorrectVersion\\chromedriver_win32 (2)\\chromedriver.exe");
        webDriver = new ChromeDriver();
        this.webDriver.get("https://finance.yahoo.com/most-active");
//        WebElement elementOk = this.webDriver.findElement(By.xpath("//*[@id=\"consent\"]/div/div/div[3]/form/div/button[1]"));
        WebElement elementOk = this.webDriver.findElement(By.xpath("/html/body/div/div/div/form/div/button[2]"));
        elementOk.click();
        List<String> listWihtFirstFive = new ArrayList<>();
        synchronized (webDriver) {
            WebElement orderElement = this.webDriver.findElement((By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/thead/tr/th[5]")));
            orderElement.click();
            this.webDriver.wait(2000);
            orderElement.click();
            this.webDriver.wait(2000);
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[1]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[2]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[3]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[4]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[5]/td[1]")).getText());
        }
        webDriver.close();
        return listWihtFirstFive;
    }


    private List<Action> getListWithPreaparedActionForDatabase(String e) throws ParseException, InterruptedException {
        String url = getUrlAfterReplace(e);
        List<Action> listWithAction = new ArrayList<>();
        Map<String, List<String>> firstResult = (LinkedHashMap) restTemplate.getForObject(url, Object.class);
        Map<String, String> metaDataAction = (LinkedHashMap) firstResult.get("Meta Data");
        String actionName = (metaDataAction.get("2. Symbol"));
        Map<String, List<String>> valuesAction = (LinkedHashMap) firstResult.get("Time Series (1min)");
        for (String key : valuesAction.keySet()) {
            if (checkDateAction(key)) {
                Action action = new Action();
                action.setName(actionName);
                Map<String, String> details = (LinkedHashMap) valuesAction.get(key);
                action.setDate(key);
                action.setOpen(details.get("1. open"));
                action.setHigh(details.get("2. high"));
                action.setLow(details.get("3. low"));
                action.setClose(details.get("4. close"));
                action.setVolume(details.get("5. volume"));
                listWithAction.add(action);
            }
        }
        return listWithAction;
    }

    private static boolean checkDateAction(String a) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date current = simpleDateFormat.parse(a);
        Date yesterDay = Date.from(Instant.now().minus(2, ChronoUnit.DAYS));
        return current.after(yesterDay);
    }

    private String getUrlAfterReplace(String e) {
        String urlForReplace = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=AAAA&interval=1min&outputsize=full&apikey=LT03AFB00U1IYSCG";
        return urlForReplace.replace("AAAA", e);
    }
}
