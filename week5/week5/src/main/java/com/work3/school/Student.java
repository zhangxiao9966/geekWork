package com.work3.school;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private int id;
    private String name;

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

    public String getInfo() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        map.put("name", name);
        return " student Info :" + map.toString();
    }
}
