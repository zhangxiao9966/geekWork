package com.work3.school;

import java.util.List;

public class School {

    private List<Klass> classes;

    public List<Klass> getClasses() {
        return classes;
    }

    public void setClasses(List<Klass> classes) {
        this.classes = classes;
    }

    public String getInfo() {
        StringBuffer buffer = new StringBuffer();
        classes.forEach(klass -> buffer.append(klass.getInfo()));
        return " school Info :" + buffer.toString();
    }
}
