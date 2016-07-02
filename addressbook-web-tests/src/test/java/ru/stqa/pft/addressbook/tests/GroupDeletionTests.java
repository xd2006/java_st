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
public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().gotoGroupPage();
            app.group().create(new GroupData().withName("group name").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testGroupDeletion() {

        app.goTo().gotoGroupPage();
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups();
        assertEquals(after.size(), before.size() - 1, "Group wasn't deleted");
        assertThat(before.without(deletedGroup), equalTo(after));

    }


}
