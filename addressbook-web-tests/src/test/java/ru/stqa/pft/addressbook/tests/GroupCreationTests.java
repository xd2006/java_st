package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {



    @DataProvider
    public Iterator<Object[]> validGroupsXML() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("data.validGroupsXML"))))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsJson() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("data.validGroupsJson"))))) {
            String json = "";
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();

            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());//(List<GroupData>) gson.fromJson(json,GroupData.class);
            return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @Test(dataProvider = "validGroupsJson")
    public void testGroupCreation(GroupData group) {

        app.goTo().gotoGroupPage();
        Groups before = app.group().getAll();
        app.group().create(group);
        Groups after = app.group().getAll();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

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
