package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Alex on 29.05.2016.
 */
public class SessionHelper extends BaseHelper {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));

    }

}
