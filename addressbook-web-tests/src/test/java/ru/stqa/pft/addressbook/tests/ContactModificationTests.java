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
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withName("name").withMiddleName("maname").withLastName("last name")
                    .withNickname("nickname").withTitle("title").withCompany("company").withAddress("address").withMobilePhone("33333")
                    .withEmail("mail@company.com").withHomepage("www.homepage.com").withWorkPhone("1111").withHomePhone("2222"));
        }
    }

    @Test
    public void testContactModification() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("name edit 1").withMiddleName("maname edit 1").withLastName("last name edit 1").withNickname(
                "nickname edit").withTitle("title edit").withCompany("company edit").withAddress("address edit").withMobilePhone("88888").withWorkPhone("1111(2)").withHomePhone("2222(5)")
                .withEmail("mail_edit@company.com").withHomepage("www.homepage_edit.com");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(before.withModified(contact), equalTo(after));
    }


}
