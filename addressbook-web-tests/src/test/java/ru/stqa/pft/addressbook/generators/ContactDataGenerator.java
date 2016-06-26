package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex on 26.06.2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {

        List<ContactData> contacts = generateContacts(count);

        if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format: " + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();

    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();

    }


    private List<ContactData> generateContacts(int count) {
        List<File> files = new ArrayList<File>();
        files.add(new File("src/test/resources/473.gif"));
        files.add(new File("src/test/resources/473a.gif"));
        files.add(new File("src/test/resources/ava.jpg"));
        files.add(new File("src/test/resources/back.gif"));

        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            File photo = files.get(ThreadLocalRandom.current().nextInt(0,files.size()));
            contacts.add(new ContactData().withName(String.format("test %s", i)).withMiddleName(String.format("mname %s", i)).withLastName(String.format("last name %s", i))
                    .withNickname(String.format("nickname %s", i)).withTitle(String.format("title %s", i)).withCompany(String.format("company %s", i)).withAddress(String.format("address \n 220 %s", i))
                    .withMobilePhone(String.format("+345 987%s", i)).withEmail(String.format("test%s@company.com", i)).withHomepage(String.format("homepage_%s.com", i)).withWorkPhone(String.format("+345(10)87%s", i))
                    .withHomePhone(String.format("+3651-08-7%s", i)).withPhotoPath(photo.getPath()));
        }

        return contacts;
    }
}
