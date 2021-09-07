package com.custom.starter;

import java.util.ArrayList;
import java.util.List;

public class Klass {
    private String id;

    public Klass(String id) {
        this.id = id;
    }

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getId() {
        return id;
    }

    public String getInfo() {
        StringBuffer buffer = new StringBuffer();
        students.forEach(student -> buffer.append(student.getInfo()));
        return " klass Info :" + id + buffer.toString();
    }
}
