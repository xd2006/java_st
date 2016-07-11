package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

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
    public void testResetPassword() throws IOException {
        User user = app.db().users().listIterator().next();
        long currentTime = System.currentTimeMillis();
        String password = "password" + currentTime;
        app.nav().logIn(app.properties.getProperty("web.adminLogin"), app.properties.getProperty("web.adminPassword"));
        app.nav().gotoUsersManagement();
        app.users().resetUserPassword(user.username);
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user.username, password));


    }
}
