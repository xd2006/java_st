package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("work"),contactData.getWorkPhone());
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

        wd.findElement(By.xpath(String.format(".//*[@id='maintable']/tbody/tr[.//input[@id='%s']]/td[8]/a/img", id))).click();

    }

    public void openContactDetails(int id) {

        wd.findElement(By.xpath(String.format(".//*[@id='maintable']/tbody/tr[.//input[@id='%s']]/td[7]/a/img", id))).click();

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
        contactCache = null;
        app.contact().returnToHomePage();
    }

    public void delete(ContactData contact) {
        app.contact().selectContact(contact.getId());
        app.contact().deleteContact();
        app.contact().acceptAlert();
        contactCache = null;
        app.goTo().homePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactFormCreation();
        contactCache = null;
        returnToHomePage();
    }

    public int getContactsCount() {
        setTimeout(2);
        int count =  wd.findElements(By.name("selected[]")).size();
        setTimeout(30);
        return count;
    }

    private Contacts contactCache = null;

    public Contacts getAll() {

            if (contactCache !=null) {
                return new Contacts(contactCache);
            }

        contactCache = new Contacts();
        setTimeout(2);
        List<WebElement> elements = wd.findElements(By.xpath(".//*[@id='maintable']/tbody/tr[@name='entry']"));
        setTimeout(30);
        if (elements.size()>0) {
            for (WebElement element : elements) {
                int id = Integer.parseInt(element.findElement(By.xpath("./td[1]/input")).getAttribute("value"));
                String lastName = element.findElement(By.xpath("./td[2]")).getText();
                String name = element.findElement(By.xpath("./td[3]")).getText();
                String phones = element.findElement(By.xpath("./td[6]")).getText();
                String emails = element.findElement(By.xpath("./td[5]")).getText();
                String address = element.findElement(By.xpath("./td[4]")).getText();
                ContactData contact = new ContactData().withId(id).withName(name).withLastName(lastName)
                        .withAllPhones(phones).withAllEmails(emails).withAddress(address);


                contactCache.add(contact);
            }
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {

        initContactModification(contact.getId());
        String firstName = getValue(By.name("firstname"));
        String middleName = getValue(By.name("middlename"));
        String lastName = getValue(By.name("lastname"));
        String nickName = getValue(By.name("nickname"));
        String title = getValue(By.name("title"));
        String company = getValue(By.name("company"));
        String address = getValue(By.name("address"));
        String mobile = getValue(By.name("mobile"));
        String home = getValue(By.name("home"));
        String work = getValue(By.name("work"));
        String email = getValue(By.name("email"));
        String email2 = getValue(By.name("email2"));
        String email3 = getValue(By.name("email3"));
        String homepage = getValue(By.name("homepage"));
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstName).withMiddleName(middleName).withLastName(lastName)
                .withNickname(nickName).withTitle(title).withCompany(company).withAddress(address).withMobilePhone(mobile)
                .withHomepage(homepage).withHomePhone(home).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public String infoFromDetailsForm(ContactData contact) {
        openContactDetails(contact.getId());
        return wd.findElement(By.id("content")).getText();

    }
}
