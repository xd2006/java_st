package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        //skipIfNotFixed(6);
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withName("name").withMiddleName("maname").withLastName("last name")
                    .withNickname("nickname").withTitle("title").withCompany("company").withAddress("address").withMobilePhone("33333")
                    .withEmail("mail@company.com").withHomepage("www.homepage.com").withWorkPhone("1111").withHomePhone("2222"));
        }
    }


    @Test
    public void testContactDeletion() throws IOException {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData contactToDelete = before.iterator().next();
        app.contact().delete(contactToDelete);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(before.without(contactToDelete), equalTo(after));
        verifyContactListInUI();

    }


}
