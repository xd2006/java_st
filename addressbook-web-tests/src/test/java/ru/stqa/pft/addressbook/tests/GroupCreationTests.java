package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().gotoGroupPage();
        Set<GroupData> before = app.group().getAll();

        GroupData group = new GroupData().withName("group name 2").withHeader("header").withFooter("footer");
        app.group().create(group);
        Set<GroupData> after = app.group().getAll();
        Assert.assertEquals(after.size(), before.size() + 1, "Group wasn't created");
        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);

        Assert.assertEquals(before,after);


    }
}
