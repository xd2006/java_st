package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions()
    {
        app.goTo().homePage();
        if (app.contact().getAll().size()==0) {
            app.contact().create(new ContactData().withName("name").withMiddleName("maname").withLastName("last name")
                    .withNickname("nickname").withTitle("title").withCompany("company").withAddress("address")
                    .withMobilePhone("mobile").withEmail("mail@company.com").withHomepage("www.homepage.com"));
        }
    }


    @Test
    public void testContactDeletion()
    {
        Set<ContactData> before = app.contact().getAll();
        ContactData contactToDelete = before.iterator().next();

        app.contact().delete(contactToDelete);

        Set<ContactData> after = app.contact().getAll();
        Assert.assertEquals(after.size(),before.size()-1, "Contact wasn't deleted");

        before.remove(contactToDelete);
        Assert.assertEquals(before,after);

    }


}
