package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Alex on 29.05.2016.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void logIn(String username, String email){
        wd.get(app.getProperty("web.baseUrl"));
        type(By.name("username"),username);
        type(By.name("password"),email);
        click(By.xpath(".//*[@id='login-form']//input[@class='button'and @type='submit']"));
    }


    public void gotoUsersManagement() {
        By headerLocator = By.xpath(".//*[@id='manage-user-div']/form[1]/fieldset/input[@class='button-small'and @type='submit']");
        if (isElementPresent(headerLocator))
            return;
        click(By.xpath(".//*[@id='manage-menu']//a[contains(@href,'manage_user_page.php')"));

    }




}
