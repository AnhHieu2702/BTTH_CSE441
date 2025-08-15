package com.hungthinh.parsexml;

public class Employee {
    private int id;
    private String title;
    private String name;
    private String phone;

    public Employee() {
    }

    public Employee(int id, String title, String name, String phone) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
