package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by Alex on 10.07.2016.
 */
public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

   @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

    @Test
    public void testRegistration() throws IOException, ServiceException {
        skipIfNotFixed(1);
        long currentTime = System.currentTimeMillis();
        String email = "user"+currentTime+"@localhost.localdomain";
        String user = "user"+currentTime;
        String password = "password";
        //app.james().createUser(user,password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
       // List<MailMessage> mailMessages = app.james().waitForMail(user, password,60000);
                String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user,password));
    }




}
