package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Alex on 10.07.2016.
 */
public class ReqistrationHelper extends BaseHelper{

    public ReqistrationHelper(ApplicationManager app) { super (app);}


    public void start(String username, String email){
        wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
        type(By.id("username"),username);
        type(By.id("email-field"),email);
        click(By.xpath(".//*[@id='signup-form']//input[@class='button'and @type='submit']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.xpath(".//*[@id='account-update-form']//input[@class='button'and @type='submit']"));
    }
}


