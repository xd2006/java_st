package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.User;

/**
 * Created by Alex on 11.07.2016.
 */
public class ManagePasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

    @Test
    public void testResetPassword(){
    User user = app.db().users().listIterator().next();
        app.nav().logIn(app.properties.getProperty("web.adminLogin"),app.properties.getProperty("web.adminPassword"));
        app.nav().gotoUsersManagement();

    }

}
