package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.*;

@XStreamAlias("contact")

@Entity
@Table( name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column (name = "firstname")
    private String name;
    @Expose
    @Column (name = "middlename")
    private String middleName;
    @Expose
    @Column (name = "lastname")
    private String lastName;
    @Expose
    @Column (name = "nickname")
    private String nickname;
    @Expose
    @Column (name = "title")
    private String title;
    @Expose
    @Column (name = "company")
    private String company;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Type(type = "text")
    @Column (name = "email")
    private String email="";
    @Expose
    @Type(type = "text")
    @Column (name = "email2")
    private String email2="";
    @Expose
    @Type(type = "text")
    @Column (name = "email3")
    private String email3="";
    @Expose
    @Type(type = "text")
    @Column (name = "homepage")
    private String homepage;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Transient
    private String allEmails;
    @Expose
    @Type(type = "text")
    @Column (name = "address")
    private String address;
    @Expose
    @Type(type = "text")
    @Column (name = "photo")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    public ContactData withGroup(GroupData group) {
        Collection<GroupData> groupsCol = new ArrayList<GroupData>(){};
        groupsCol.add(group);
        this.groups = new HashSet<GroupData>(groupsCol);
        return this;
    }

    public ContactData withGroup(Groups group) {
        this.groups = new HashSet<GroupData>(group);
        return this;
    }

    public void withAddedGroup(GroupData group) {
        this.groups.add(group);
    }

    public void withoutGroup(GroupData group) {
        this.groups.remove(group);
    }

    public Groups getGroups() {

        return new Groups(groups);
    }

    public File getPhoto() {
        if (photo!=null && photo!="") {
            return new File(photo);

        }
        return null;
    }


    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }


    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
        if (homepage != null ? !homepage.equals(that.homepage) : that.homepage != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
        if (allEmails != null ? !allEmails.equals(that.allEmails) : that.allEmails != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return groups != null ? groups.equals(that.groups) : that.groups == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
        result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
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


    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", homepage='" + homepage + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
