package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Alex on 29.05.2016.
 */
public class NavigationHelper extends BaseHelper{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage()
    {
        click(By.linkText("groups"));
    }


}
