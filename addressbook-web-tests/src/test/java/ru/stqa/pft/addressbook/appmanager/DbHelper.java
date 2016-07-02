package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.List;

/**
 * Created by Alex on 02.07.2016.
 */
public class DbHelper {
    protected ApplicationManager app;
    private final SessionFactory sessionFactory;

    public DbHelper(ApplicationManager app) {
        this.app = app;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File(app.properties.getProperty("db.hibernateCfg"))) // configures settings
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData group : result) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();
        return new Groups(result);

    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        for (ContactData contact : result) {
            String cleanedAddress = contact.getAddress().replaceAll("\r", "");
            contact.withAddress(cleanedAddress);
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();

        return new Contacts(result);

    }

}




