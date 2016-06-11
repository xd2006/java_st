package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion()
    {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name", "maname", "last name", "nickname", "title", "company", "address", "mobile", "mail@company.com", "www.homepage.com",null));
        }
        int before = app.getContactHelper().getContactsCount();
        app.getContactHelper().selectContact(before-1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactsCount();
        Assert.assertEquals(after,before-1, "Contact wasn't deleted");

    }
}
