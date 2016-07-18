package ru.stqa.pft.addressbook.model;

/**
 * Created by Alex on 18.07.2016.
 */
public class IssueBugify {

    private int id;
    private String subject;
    private String description;
    private int state;

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueBugify that = (IssueBugify) o;

        if (id != that.id) return false;
        if (state != that.state) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + state;
        return result;
    }

    public int getState() {

        return state;
    }

    public IssueBugify withState(int state) {
        this.state = state;
        return this;
    }

    public IssueBugify  withId(int id) {
        this.id = id;

        return this;
    }

    public String getSubject() {
        return subject;
    }

    public IssueBugify  withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IssueBugify  withDescription(String description) {
        this.description = description;
        return this;
    }
}
