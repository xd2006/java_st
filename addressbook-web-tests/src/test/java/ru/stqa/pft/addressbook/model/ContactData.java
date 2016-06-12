package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
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

    public ContactData(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.middleName = null;
        this.lastName = lastName;
        this.nickname = null;
        this.title = null;
        this.company = null;
        this.address = null;
        this.mobilePhone = null;
        this.email = null;
        this.homepage = null;
        this.group = null;
    }

    public ContactData(String name, String middleName, String lastName, String nickname, String title, String company, String address, String mobilePhone, String email, String homepage, String group) {
        this.id = Integer.MAX_VALUE;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }
}
