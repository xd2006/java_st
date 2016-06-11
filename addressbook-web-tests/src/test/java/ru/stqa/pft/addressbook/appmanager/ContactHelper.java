package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactHelper extends BaseHelper {

    public ContactHelper(ApplicationManager app) {
        super(app);
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
        if (creation) {
            Assert.assertTrue(isElementPresent(By.name("new_group")));
            select(By.name("new_group"), contactData.getGroup());
        } else {
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

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactFormCreation();
        returnToHomePage();
        //app.getNavigationHelper().gotoHomePage();
    }

    public int getContactsCount() {
        setTimeout(2);
        int count =  wd.findElements(By.name("selected[]")).size();
        setTimeout(30);
        return count;
    }
}
