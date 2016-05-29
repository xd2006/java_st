package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Alex on 29.05.2016.
 */
public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion()
    {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().acceptAlert();

    }
}
