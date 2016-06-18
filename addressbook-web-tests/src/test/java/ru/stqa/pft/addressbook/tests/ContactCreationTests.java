package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().getAll();
        ContactData contact = new ContactData().withName("name").withMiddleName("maname").withLastName("last name")
                .withNickname("nickname").withTitle("title").withCompany("company").withAddress("address")
                .withMobilePhone("mobile").withEmail("mail@company.com").withHomepage("www.homepage.com");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().getAll();
        Assert.assertEquals(after.size(),before.size()+1, "Contact wasn't created");
        contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(contact);

        Assert.assertEquals(before,after);

    }


}
