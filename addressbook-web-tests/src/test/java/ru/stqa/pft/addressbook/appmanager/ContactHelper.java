package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactFormCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {

        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("homepage"), contactData.getHomepage());
        if (creation)
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        else {

            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }


    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void returnToHomePage() {
        if (!isElementPresent(By.id("maintable"))) click(By.linkText("home page"));
    }

    public void selectContact() {
        clickRegularCheckBox();
    }


    public void initContactModification() {

        click(By.xpath(".//*[@id='maintable']/tbody/tr/td[8]/a/img"));

    }

    public void submitContactFormModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//form[2]/div[2]/input[@value='Delete']"));
    }
}
