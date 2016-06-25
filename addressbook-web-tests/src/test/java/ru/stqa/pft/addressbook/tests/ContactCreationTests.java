package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().getAll();
        File photo = new File("src/test/resources/473.gif");
        ContactData contact = new ContactData().withName("name").withMiddleName("maname").withLastName("last name")
                .withNickname("nickname").withTitle("title").withCompany("company").withAddress("address")
                .withMobilePhone("11111").withEmail("mail@company.com").withHomepage("www.homepage.com").withWorkPhone("33333")
                .withHomePhone("44444").withPhoto(photo);
        app.contact().create(contact);
        Set<ContactData> after = app.contact().getAll();
        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())),equalTo(after));

    }


}
