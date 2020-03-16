package com.sourabh.coronavirustracker;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CoronavirusTrackerApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoronavirusDataTest {

    @LocalServerPort
    private int port;
    private static final String URL = "http://localhost:";

    private static ChromeDriverService chromeDriverService;
    private static RemoteWebDriver driver;

    @BeforeClass
    public static void setUpDriver() throws IOException {

        chromeDriverService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("drivers\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();

        chromeDriverService.start();

        driver = new RemoteWebDriver(chromeDriverService.getUrl(), new ChromeOptions());
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void stopService() {
        driver.close();
        driver.quit();
        chromeDriverService.stop();
    }

    @Test
    public void urlTest() {
        driver.get(URL + port);
        var expected = "Coronavirus Tracker";
        Assert.assertEquals(expected, driver.getTitle());
    }

}
