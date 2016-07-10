package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 29.05.2016.
 */
public class BaseHelper {
    protected WebDriver wd;
    protected ApplicationManager app;

    public BaseHelper(ApplicationManager app) {
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null) {
            String currentText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(currentText)) {
                click(locator);
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) {

                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }
        }


    protected String getValue(By locator)
    {
        return wd.findElement(locator).getAttribute("value");
    }

    protected void clickRegularCheckBox(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]"));
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            setTimeout(1);
            wd.findElement(locator);
           setTimeout(60);
            return true;
        } catch (NoSuchElementException e) {
            setTimeout(60);
            return false;
        }

    }

    protected void select(By selectLocator, String itemText) {
        if (itemText != null)
            new Select(wd.findElement(selectLocator)).selectByVisibleText(itemText);
    }

    protected void setTimeout(int timeoutSeconds) {

        wd.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
    }


}
