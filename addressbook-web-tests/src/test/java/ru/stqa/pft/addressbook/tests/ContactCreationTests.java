package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

    @DataProvider
    public Iterator<Object[]> validContactsXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("data.validContactsXML"))))){
            String xml = "";
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                xml += line;
                line = reader.readLine();
            }

            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.properties.getProperty("data.validContactsJson"))))){
            String json = "";
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();

            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsJson")
    public void testContactCreation(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().getAll();
        contact.withPhoto(contact.getPhoto());
        app.contact().create(contact);
        Set<ContactData> after = app.contact().getAll();
        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt())),equalTo(after));

    }


}
