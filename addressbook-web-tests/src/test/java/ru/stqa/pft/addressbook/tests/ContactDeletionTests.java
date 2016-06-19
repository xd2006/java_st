package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        Contacts before = app.contact().getAll();
        ContactData contactToDelete = before.iterator().next();

        app.contact().delete(contactToDelete);

        Contacts after = app.contact().getAll();
        assertThat(after.size(),equalTo(before.size()-1));
        assertThat(before.without(contactToDelete),equalTo(after));

    }


}
