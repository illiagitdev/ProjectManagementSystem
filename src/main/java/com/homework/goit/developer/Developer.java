package com.homework.goit.developer;

import com.homework.goit.common.Entity;

import java.sql.Date;

public class Developer extends Entity {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Sex sex;
    private Date hireDate;
    private int companyId;
    private int salary;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex.getSex();
    }

    public void setSex(String sex) {
        sex = sex.toLowerCase();
        if (sex.equals(Sex.MALE.getSex())){
            this.sex = Sex.MALE;
        }else if (sex.equals(Sex.FEMALE.getSex())){
            this.sex =  Sex.FEMALE;
        } else {
            this.sex = Sex.OTHER;
        }
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        if (companyId < 0 ){
            this.companyId = 0;
        } else {
            this.companyId = companyId;
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if (salary < 0){
            this.salary = 0;
        }
        else {
            this.salary = salary;
        }
    }

    @Override
    public String toString() {
        return "Developer{" +
                super.toString() + ' ' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", sex='" + sex.getSex() + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", companyId=" + companyId +
                ", salary=" + salary +
                '}';
    }
}
