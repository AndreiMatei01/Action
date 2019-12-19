package panacotari.demo;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
class ActionsApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private WebDriver webDriver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aimatei\\Downloads\\chromCorrectVersion\\chromedriver_win32 (2)\\chromedriver.exe");
        webDriver = new ChromeDriver();

    }

    @Test
    public void getSearchPage() throws InterruptedException {
        this.webDriver.get("https://finance.yahoo.com/most-active");
        WebElement elementOk = this.webDriver.findElement(By.xpath("/html/body/div/div/div/form/div/button[2]"));
        elementOk.click();
        List<String> listWihtFirstFive= new ArrayList<>();
        synchronized (webDriver) {
            WebElement orderElement = this.webDriver.findElement((By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/thead/tr/th[5]")));
            orderElement.click();
            this.webDriver.wait(2000);
            orderElement.click();
            this.webDriver.wait(2000);
            WebElement webElement = this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[1]/td[2]"));

            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[1]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[2]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[3]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[4]/td[1]")).getText());
            listWihtFirstFive.add(this.webDriver.findElement(By.xpath("//*[@id=\"scr-res-table\"]/div[1]/table/tbody/tr[5]/td[1]")).getText());
        }

        System.out.println(listWihtFirstFive);
    }

    private void assertNotNull(WebElement element) {
    }

}
