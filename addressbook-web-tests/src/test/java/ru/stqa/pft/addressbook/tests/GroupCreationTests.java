package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().gotoGroupPage();
        Groups before = app.group().getAll();
        GroupData group = new GroupData().withName("group name 2").withHeader("header").withFooter("footer");
        app.group().create(group);
        Groups after = app.group().getAll();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));


    }

    @Test
    public void testBadGroupCreation() {

        app.goTo().gotoGroupPage();
        Groups before = app.group().getAll();
        GroupData group = new GroupData().withName("group name 2'").withHeader("header").withFooter("footer");
        app.group().create(group);
        assertThat(app.group().getGroupCount(), equalTo(before.size()));
        Groups after = app.group().getAll();
               assertThat(after, equalTo(before));


    }

}
