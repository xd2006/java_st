package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.User;

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


    public List<User> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User where enabled = 1 and username!= 'administrator' and access_level = 25").list();
        for (User user : result) {
            System.out.println(user);
        }
        session.getTransaction().commit();
        session.close();

        return result;

    }

}




