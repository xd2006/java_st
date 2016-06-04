package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String mobilePhone;
    private final String email;
    private final String homepage;
    private String group;

    public ContactData(String name, String middleName, String lastName, String nickname, String title, String company, String address, String mobilePhone, String email, String homepage, String group) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.homepage = homepage;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getGroup() {
        return group;
    }
}
