package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alex on 19.06.2016.
 */
public class ContactPhoneTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions()
    {
        app.goTo().homePage();
        if (app.contact().getAll().size()==0) {
            app.contact().create(new ContactData().withName("name").withMiddleName("maname").withLastName("last name")
                    .withNickname("nickname").withTitle("title").withCompany("company").withAddress("address").withMobilePhone("+33-333-988")
                    .withEmail("mail@company.com").withHomepage("www.homepage.com").withWorkPhone("11 11 977").withHomePhone("2 (222) 765-12-34"));
        }
    }

    @Test
    public void testContactPhones()
    {
        app.goTo().homePage();
        ContactData contact = app.contact().getAll().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(),equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter(s->!s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("[^+\\d]","");
    }

}
