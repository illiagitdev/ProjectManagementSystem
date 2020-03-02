package com.homework.goit.customer;

import com.homework.goit.common.Entity;

public class Customer extends Entity {
    private String name;
    private int budget;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }
}
