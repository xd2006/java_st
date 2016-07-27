package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by Alex on 29.05.2016.
 */
@Listeners(MyTestListener.class)
public class TestBase {

    Logger logger= LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", FIREFOX));
    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    @BeforeSuite
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app",app);
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

    public boolean isIssueOpen(int issueId) throws IOException {
        IssueBugify issueData = getIssue(issueId);
        int status = issueData.getState();
        if (status == 3 || status == 2){
                return false;
        }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private IssueBugify getIssue(int issueId) throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues/"+issueId+".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<IssueBugify> set = new Gson().fromJson(issues, new TypeToken<Set<IssueBugify>>() {
        }.getType());
        return set.iterator().next();
    }


}


