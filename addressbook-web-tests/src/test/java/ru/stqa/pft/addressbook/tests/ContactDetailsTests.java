package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alex on 20.06.2016.
 */
public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions()
    {
        app.goTo().homePage();
        if (app.contact().getAll().size()==0) {
            app.contact().create(new ContactData().withName("name").withLastName("last name")
                    .withAddress("  address, 22000 avn\n" +
                            "DDF\n" +
                            "    , 888878 -+ TY(98A)  ").withMobilePhone("  +33-333- 988 ")
                    .withEmail("mail@company.com  ").withEmail2("  mail2@company.com").withEmail3(" private_mail@company.com  ").withHomepage("www.homepage.com").withWorkPhone("11 11 977  ").withHomePhone("2 (222) 765-12-34"));
        }
    }

    @Test
    public void testContactDetails()
    {
        app.goTo().homePage();
        ContactData contact = app.contact().getAll().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);
        assertThat(contactInfoFromDetailsForm,equalTo(mergeContactData(contactInfoFromEditForm)));
    }


    private String mergeContactData(ContactData contact) {
       return mergeNames(contact)+"\n"+cleanedWhitespaces(contact.getAddress().trim())+"\n\n"+mergePhones(contact)+"\n\n"+
               mergeEmails(contact)+"\n"+getHomePage(contact);


    }

    private String getHomePage(ContactData contact) {
        return contact.getHomepage()=="" ? "":"Homepage:\n"+contact.getHomepage().trim();
    }


    private String mergePhones(ContactData contact) {
        String homePhone = contact.getHomePhone()=="" ? "":"H: "+contact.getHomePhone().trim();
        String mobilePhone = contact.getMobilePhone()=="" ? "":"M: "+contact.getMobilePhone().trim();
        String workPhone = contact.getWorkPhone()=="" ? "":"W: "+contact.getWorkPhone().trim();

        return Arrays.asList(homePhone,mobilePhone,workPhone)
                .stream().filter(s->!s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    private String mergeNames(ContactData contact) {
        return Arrays.asList(contact.getName().trim(),contact.getLastName().trim())
                .stream().filter(s->!s.equals(""))
                .collect(Collectors.joining(" "));
    }
    private String mergeEmails(ContactData contact) {
        String email = contact.getEmail()=="" ? "": contact.getEmail().trim()+" (www."+contact.getEmail().split("@")[1]+")";
        String email2 = contact.getEmail2()=="" ? "": contact.getEmail2().trim()+" (www."+contact.getEmail2().split("@")[1]+")";
        String email3 = contact.getEmail3()=="" ? "": contact.getEmail3().trim()+" (www."+contact.getEmail3().split("@")[1]+")";

        return Arrays.asList(email.trim(),email2.trim(),email3.trim())
                .stream().filter(s->!s.equals(""))
                .map(ContactDataTests::shortenedWhitespaces)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanedWhitespaces(String string){
        return string.replaceAll("\\n\\s+","\n").replaceAll("\\s+\\n","\n");
    }

}
