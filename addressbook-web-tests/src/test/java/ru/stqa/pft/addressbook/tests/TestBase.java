package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by Alex on 29.05.2016.
 */
public class TestBase {

    Logger logger= LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Start test "+m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestEnd(Method m, Object[] p) {
        logger.info("Stop test "+m.getName()+" with parameters "+ Arrays.asList(p));
    }

}


