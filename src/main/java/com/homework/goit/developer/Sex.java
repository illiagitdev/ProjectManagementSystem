package com.homework.goit.developer;

public enum Sex {
    MALE("male"),
    FEMALE("female");

    private String sex;

    Sex(String sex) {
        this.sex = sex;
    }

    String getSex(){
        return sex;
    }
}
