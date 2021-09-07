package com.custom.starter;

import java.util.List;

public class School {

    private List<Klass> classes;

    public School(List<Klass> myClasses) {
        this.classes = myClasses;
    }

    public String getInfo() {
        StringBuffer buffer = new StringBuffer();
        classes.forEach(klass -> buffer.append(klass.getInfo()));
        return " school Info :" + buffer.toString();
    }
}
