package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Alex on 29.05.2016.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void gotoGroupPage() {
        By headerLocator = By.xpath("//h1[.='Groups']");
        if (isElementPresent(headerLocator)
                && isElementPresent(By.name("new")))
            return;
        click(By.linkText("groups"));
    }

    public void homePage() {
        if (!isElementPresent(By.id("maintable"))) click(By.linkText("home"));
    }


}
