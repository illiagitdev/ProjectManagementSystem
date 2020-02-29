package com.homework.goit.project;

import com.homework.goit.common.Entity;

import java.sql.Date;

public class Project extends Entity {
    private String name;
    private Date releaseDate;
    private int cost;
    private Date  projectStart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", cost=" + cost +
                ", projectStart=" + projectStart +
                '}';
    }
}
