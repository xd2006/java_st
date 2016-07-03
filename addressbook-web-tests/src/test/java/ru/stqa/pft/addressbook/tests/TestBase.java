package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by Alex on 29.05.2016.
 */
public class TestBase {

    Logger logger= LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", FIREFOX));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Start test "+m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestEnd(Method m, Object[] p) {
        logger.info("Stop test "+m.getName()+" with parameters "+ Arrays.asList(p));
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().getAll();
            assertThat(uiGroups, equalTo(dbGroups.stream().map(g -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().getAll(false);
            assertThat(uiContacts, equalTo(dbContacts.stream().map(c -> new ContactData().withId(c.getId()).withName(c.getName()).withLastName(c.getLastName())
                   .withAddress(c.getAddress().replaceAll("\\n\\s+", "\n").replaceAll("\\s+\\n", "\n"))).collect(Collectors.toSet())));
        }
    }
}


