package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("name", "maname", "last name", "nickname", "title", "company", "address", "mobile", "mail@company.com", "www.homepage.com",null));
    }

}
