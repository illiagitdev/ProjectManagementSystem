package com.homework.goit.company;

import com.homework.goit.common.Entity;

public class Company extends Entity {
    private String name;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Company{" +
                super.toString() + ' ' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
