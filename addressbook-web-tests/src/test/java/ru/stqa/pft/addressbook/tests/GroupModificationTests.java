package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Alex on 29.05.2016.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().gotoGroupPage();
            app.group().create(new GroupData().withName("group name").withHeader("header").withFooter("footer"));
        }
    }


    @Test
    public void testGroupModification() {
        app.goTo().gotoGroupPage();
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("name edited").withHeader("header edited").withFooter("footer edited");
        app.group().modify(group);
        Groups after = app.db().groups();
        assertEquals(before.size(), after.size());
        assertThat(after, equalTo(before.withModified(group)));
        verifyGroupListInUI();


    }


}
