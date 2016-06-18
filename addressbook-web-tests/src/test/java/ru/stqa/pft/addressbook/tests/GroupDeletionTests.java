package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by Alex on 29.05.2016.
 */
public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions()
    {
        app.goTo().gotoGroupPage();
        if (app.group().getAll().size()==0) {
            app.group().create(new GroupData().withName("group name").withHeader("header").withFooter("footer"));
        }
    }


    @Test
    public void testGroupDeletion() {

        Set<GroupData> before = app.group().getAll();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().getAll();
        Assert.assertEquals(after.size(),before.size()-1, "Group wasn't deleted");

        before.remove(deletedGroup);
            Assert.assertEquals(before,after);

    }


}
