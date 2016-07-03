package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Alex on 03.07.2016.
 */
public class ContactInGroupsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withName("name").withMiddleName("maname").withLastName("last name")
                    .withNickname("nickname").withTitle("title").withCompany("company").withAddress("address").withMobilePhone("33333")
                    .withEmail("mail@company.com").withHomepage("www.homepage.com").withWorkPhone("1111").withHomePhone("2222"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().gotoGroupPage();
            app.group().create(new GroupData().withName("group name").withHeader("header").withFooter("footer"));
        }

    }

    @Test
    public void testAddContactToGroup() {
        Contacts before = app.db().contacts();
        ContactData workContact = null;
        try {
            workContact = app.db().contacts().stream().filter(c -> c.getGroups().size() < app.db().groups().size()).iterator().next();
        } catch (java.util.NoSuchElementException e) {
        }

            if (workContact == null) {
                app.goTo().gotoGroupPage();
                app.group().create(new GroupData().withName("sp test group name").withHeader("sp test header").withFooter("sp test footer"));
                workContact = app.db().contacts().stream().filter(c -> c.getGroups().size() < app.db().groups().size()).iterator().next();
            }
            final ContactData finalWorkContact = workContact;
            GroupData workGroup = app.db().groups().stream().filter(g -> !finalWorkContact.getGroups().contains(g)).iterator().next();

            app.goTo().homePage();
            app.contact().addToGroup(workContact, workGroup);
            Contacts after = app.db().contacts();
            workContact.withAddedGroups(workGroup);
            assertThat(before.withModified(workContact), equalTo(after));

    }
}

