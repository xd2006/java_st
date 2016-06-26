package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by Alex on 29.05.2016.
 */
public class TestBase {


    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
