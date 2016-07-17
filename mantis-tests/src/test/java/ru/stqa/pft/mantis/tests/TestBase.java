package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;


/**
 * Created by Alex on 29.05.2016.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", FIREFOX));

    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        IssueData issueData = app.soap().getIssueData(issueId);
        ObjectRef status = issueData.getStatus();
        ObjectRef resolution = issueData.getResolution();
       if (status.getName().equals("closed")){
           if (!resolution.getName().equals("suspended")){
               return false;
           }
       }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        //app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php","config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        //app.ftp().restore("config_inc.php.back","config_inc.php");
        app.stop();
    }

}


