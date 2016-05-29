package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void ContactCreationTests() {

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("name", "maname", "last name", "nickname", "title", "company", "address", "mobile", "mail@company.com", "www.homepage.com"));
        app.getContactHelper().submitContactForm();
        app.getContactHelper().returnToHomePage();
    }

}
