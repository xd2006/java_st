package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Alex on 11.07.2016.
 */
@Entity
@Table( name = "mantis_user_table")
public class User {

    @Id
    @Column(name = "id")
    public int id;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", id=" + id +
                ", access_level=" + access_level +
                '}';
    }

    public String username;
    public String email;
    public byte enabled;
    public short access_level;

}
