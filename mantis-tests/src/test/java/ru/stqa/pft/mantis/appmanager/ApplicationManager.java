package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 29.05.2016.
 */
public class ApplicationManager {
    public final Properties properties;
    private WebDriver wd;
    private String browser;
    private ReqistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mail;
    private NavigationHelper navigationHelper;
    private DbHelper dbHelper;
    private UsersHelper usersHelper;
    private JamesHelper jamesHelper;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }

    public void stop() {
        if (wd!=null) {
            wd.quit();
        }
    }

    public void init() throws IOException {

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public DbHelper db() {
        if (dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public UsersHelper users() {
        if (usersHelper == null) {
            usersHelper = new UsersHelper(this);
        }
        return usersHelper;
    }

    public ReqistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new ReqistrationHelper(this);
        }
        return registrationHelper;
    }

    public NavigationHelper nav() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public MailHelper mail() {
        if (mail == null) {
            mail = new MailHelper(this);
        }
        return mail;
    }

    public SoapHelper soap() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }

            wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
}
