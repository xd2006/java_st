package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name", "maname", "last name", "nickname", "title", "company", "address", "mobile", "mail@company.com", "www.homepage.com",null));
        }
        int before = app.getContactHelper().getContactsCount();
        app.getContactHelper().initContactModification(before-1);
        app.getContactHelper().fillContactForm(new ContactData("name edit", "maname edit", "last name edit",
                "nickname edit", "title edit", "company edit", "address edit", "mobile edit", "mail_edit@company.com", "www.homepage_edit.com",null),false);
        app.getContactHelper().submitContactFormModification();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactsCount();
        Assert.assertEquals(after, before, "Number of contacts shouldn't be changed");
    }

}
