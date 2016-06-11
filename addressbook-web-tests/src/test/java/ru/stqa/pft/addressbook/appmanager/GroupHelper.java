package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 29.05.2016.
 */
public class GroupHelper extends BaseHelper {

    public GroupHelper(ApplicationManager app) {
        super(app);

    }

    public void returnToGroupPage() {
        By headerLocator = By.xpath("//h1[.='Groups']");
        if (isElementPresent(headerLocator)
                && isElementPresent(By.name("new")))
            return;
        click(By.linkText("group page"));
    }

    public void submitGroupCreationForm() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModificationForm() {
        click(By.name("update"));
    }

    public void selectGroup(int index) {
        clickRegularCheckBox(index);
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void createGroup(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreationForm();
        returnToGroupPage();

    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {

        setTimeout(2);
        int count = wd.findElements(By.name("selected[]")).size();
        setTimeout(30);
        return count;

    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

        for (WebElement element : elements) {
            String name = element.getText();
            GroupData group = new GroupData(name, null, null);
            groups.add(group);
        }
        return groups;
    }
}
