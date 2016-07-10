package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by Alex on 10.07.2016.
 */
public class ReqistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public ReqistrationHelper(ApplicationManager app) {
        this.app = app;
        wd=app.getDriver();
    }

    public void start(String username, String email){
        wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
    }
}


