package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by Alex on 10.07.2016.
 */
public class LoginTests extends TestBase{

    @Test
    public void testlogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator","root1"));
        assertTrue(session.isLoggedInAs("administrator"));

    }
}
