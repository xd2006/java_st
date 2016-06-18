package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectContact(int id) {
        wd.findElement(By.xpath("//input[@id='"+id+"']")).click();
    }


    public void initContactModification(int id) {

        wd.findElement(By.xpath(".//*[@id='maintable']/tbody/tr[.//input[@id='"+id+"']]/td[8]/a/img")).click();

    }

    public void submitContactFormModification() {
        click(By.name("update"));
    }

    public void deleteContact() {
        click(By.xpath("//form[2]/div[2]/input[@value='Delete']"));
    }

    public void modify(ContactData contact) {
        app.contact().initContactModification(contact.getId());
        app.contact().fillContactForm(contact,false);
        app.contact().submitContactFormModification();
        app.contact().returnToHomePage();
    }

    public void delete(ContactData contact) {
        app.contact().selectContact(contact.getId());
        app.contact().deleteContact();
        app.contact().acceptAlert();
        app.goTo().homePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactFormCreation();
        returnToHomePage();
    }

    public int getContactsCount() {
        setTimeout(2);
        int count =  wd.findElements(By.name("selected[]")).size();
        setTimeout(30);
        return count;
    }

    public Set<ContactData> getAll() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        setTimeout(2);
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[@name='entry']"));
        setTimeout(30);
        if (elements.size()>0) {
            for (WebElement element : elements) {
                int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
                String lastName = element.findElement(By.xpath("./td[2]")).getText();
                String name = element.findElement(By.xpath("./td[3]")).getText();

                ContactData contact = new ContactData().withId(id).withName(name).withLastName(lastName);
                contacts.add(contact);
            }
        }
        return contacts;
    }
}
