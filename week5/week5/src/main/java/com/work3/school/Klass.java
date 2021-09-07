package com.work3.school;

import java.util.ArrayList;
import java.util.List;

public class Klass {
    private String name;
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        StringBuffer buffer = new StringBuffer();
        students.forEach(student -> buffer.append(student.getInfo()));
        return " klass Info :" + name + buffer.toString();
    }
}
