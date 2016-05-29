package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void ContactCreationTests() {

        initContactCreation();
        fillContactForm(new ContactData("name", "maname", "last name", "nickname", "title", "company", "address", "mobile", "mail@company.com", "www.homepage.com"));
        submitContactForm();
        returnToHomePage();
    }

}
