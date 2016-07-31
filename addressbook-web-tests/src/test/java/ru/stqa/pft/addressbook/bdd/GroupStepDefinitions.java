package ru.stqa.pft.addressbook.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by Alex on 31.07.2016.
 */

public class GroupStepDefinitions  {

    private ApplicationManager app;
    private Groups groups;
    private GroupData newGroup;

    @Before
    public void init() throws IOException {
        app = new ApplicationManager(System.getProperty("browser", FIREFOX));
        app.init();
    }

    @After
    public void stop(){
        app.stop();
        app=null;
    }

    @Given("^a set of groups$")
    public void loadGroups(){
    groups = app.db().groups();
    }

    @When("^I create a new group with name (.+), header (.+) and footer (.+)$")
    public void createGroup(String name, String header, String footer){
        newGroup = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.goTo().gotoGroupPage();
        app.group().create(newGroup);

    }
    @Then("^the new set of groups is equal to the old set with the added group$")
    public void verifyGroupCreated() {
        Groups newGroups = app.db().groups();
        assertThat(newGroups, equalTo(
                groups.withAdded(newGroup.withId(newGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
