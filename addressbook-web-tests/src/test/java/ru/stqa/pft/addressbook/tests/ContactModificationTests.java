package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {

        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("name", "maname", "last name", "nickname", "title", "company", "address", "mobile", "mail@company.com", "www.homepage.com",null));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size()-1);

        ContactData contact = new ContactData("name edit 1", "maname edit 1", "last name edit 1",
                "nickname edit", "title edit", "company edit", "address edit", "mobile edit", "mail_edit@company.com", "www.homepage_edit.com",null);
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitContactFormModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size(), "Number of contacts shouldn't be changed");
        before.remove(before.size()-1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
