package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("name edit", "maname edit", "last name edit",
                "nickname edit", "title edit", "company edit", "address edit", "mobile edit", "mail_edit@company.com", "www.homepage_edit.com"));
        app.getContactHelper().submitContactFormModification();
        app.getContactHelper().returnToHomePage();
    }

}
