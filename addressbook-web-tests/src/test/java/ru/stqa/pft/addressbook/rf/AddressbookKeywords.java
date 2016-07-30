package ru.stqa.pft.addressbook.rf;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.IOException;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by Alex on 30.07.2016.
 */
public class AddressbookKeywords {

    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private ApplicationManager app;

    public void initApplicationManager() throws IOException {
        app = new ApplicationManager(System.getProperty("browser", FIREFOX));
        app.init();
    }

    public void stopApplicationManager(){
        app.stop();
        app=null;
    }

    public int getGroupCount(){
       app.goTo().gotoGroupPage();
        return app.group().getGroupCount();
    }

    public void createGroup(String name, String header, String footer){
        app.goTo().gotoGroupPage();
        app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));

    }

}
