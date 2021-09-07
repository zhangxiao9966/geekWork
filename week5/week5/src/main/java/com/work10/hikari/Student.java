package com.work10.hikari;

import java.util.Map;

public class Student {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private Integer klass;

    public Student() {
    }

    public Student(Map<String, Object> map) {
        this.id = (Integer) map.get("id");
        this.name = (String) map.get("name");
        this.address = (String) map.get("address");
        this.phoneNumber = (String) map.get("phoneNumber");
        this.id = (Integer) map.get("klass");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getKlass() {
        return klass;
    }

    public void setKlass(Integer klass) {
        this.klass = klass;
    }

    public String getInfo() {
        return id + " " + name + " " + address + " " + phoneNumber + " " + klass;
    }
}
