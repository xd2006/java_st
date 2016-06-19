package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }


    public void delete(GroupData group) {
        app.group().selectGroupById(group.getId());
        app.group().deleteGroup();
        groupCache=null;
        app.group().returnToGroupPage();
    }


    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreationForm();
        groupCache=null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModificationForm();
        groupCache=null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    private Groups groupCache = null;

    public Groups getAll() {
        if (groupCache !=null) {
            return new Groups(groupCache);
        }

        groupCache = new Groups();
        setTimeout(2);
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        setTimeout(30);
        if (elements.size()>0) {
            for (WebElement element : elements) {
                String name = element.getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                groupCache.add(new GroupData().withId(id).withName(name));
            }
        }
        return new Groups(groupCache);
    }


}
