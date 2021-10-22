package com.movieProjectAPI.Model;

import java.util.Date;

public class Actor {
    private int id;
    private String name;
    private Date birthdate;
    private boolean isMale;

    public Actor() {}

    public Actor(int id, String name, Date birthdate, boolean isMale) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.isMale = isMale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
