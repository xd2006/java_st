package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

/**
 * Created by Alex on 29.05.2016.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions()
    {
        app.goTo().gotoGroupPage();
        if (app.group().getAll().size()==0) {
            app.group().create(new GroupData().withName("group name").withHeader("header").withFooter("footer"));
        }
    }


    @Test
    public void testGroupModification() {

        Set<GroupData> before = app.group().getAll();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("name edited").withHeader("header edited").withFooter("footer edited");

        app.group().modify(group);

        Set<GroupData> after = app.group().getAll();

        before.remove(modifiedGroup);
        before.add(group);

        Assert.assertEquals(before,after);
    }


}
