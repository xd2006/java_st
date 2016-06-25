package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {


   @DataProvider
   public Iterator<Object[]> validGroups(){
       List<Object[]> list = new ArrayList<Object[]>();
       list.add(new Object[] {new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
       list.add(new Object[] {new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
       list.add(new Object[] {new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
       return list.iterator();
   }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) {

        app.goTo().gotoGroupPage();
        Groups before = app.group().getAll();
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
