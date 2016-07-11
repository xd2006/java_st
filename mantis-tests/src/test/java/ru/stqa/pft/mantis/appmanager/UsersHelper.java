package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Alex on 29.05.2016.
 */
public class UsersHelper extends BaseHelper {

    public UsersHelper(ApplicationManager app) {
        super(app);
    }

public void resetUserPassword(String username){
    clickOnUser(username);
    resetPassword();
}

    public void clickOnUser(String userName) {
        click(By.xpath(String.format(".//*[@id='manage-user-div']/table/tbody//a[contains(.,'%s')]", userName)));
    }

    public void resetPassword(){
      click(By.xpath(".//*[@id='manage-user-reset-form']/fieldset/span/input"));
    }


}
