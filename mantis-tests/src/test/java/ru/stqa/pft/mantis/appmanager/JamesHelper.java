package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.Session;
import javax.mail.Store;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by Alex on 11.07.2016.
 */
public class JamesHelper {

    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailserver;

    public JamesHelper(ApplicationManager app){
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());

    }

    public boolean doesUserExist(String name){
        initTelnetSession();
        write("verify "+name);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User "+name+ " exist");

    }

    public void createUser(String name, String password) {
initTelnetSession();
        write("adduser " + name + " " + password);
        String result = readUntil("User " + name + " added");
        closeTelnetSession();
    }

    public void deleteUser(String name){
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + " deleted");
        closeTelnetSession();
    }

    private void closeTelnetSession() {

        write("quit");
    }

    private void initTelnetSession() {
        mailserver = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("mailserver.adminlogin");
        String password = app.getProperty("mailserver.adminpassword");
        try {
            telnet.connect(mailserver, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //1st attempt is unsuccessful - don't know why
        readUntil("login id:");
        write("");
        readUntil("Password:");
        write("");
        //2nd attempt should be successful
        readUntil("login id:");
        write(login);
        readUntil("Password:");
        write(password);

        //Read welcome message
        readUntil("Welcome "+login+".HELP for a list of commands");

    }

    private void write(String value) {
        try{
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readUntil(String pattern) {
       try {
           char lastChar = pattern.charAt(pattern.length() - 1);
           StringBuffer sb = new StringBuffer();
           char ch = (char) in.read();
           while (true) {
               System.out.print(ch);
               sb.append(ch);
               if (ch == lastChar) {
                   if (sb.toString().endsWith(pattern)) {
                       return sb.toString();
                   }
               }
               ch = (char) in.read();
           }
       } catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }


    public List<MailMessage> waitForMail(String user, String password, int i) {
        return null;
    }
}
