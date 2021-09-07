package com.custom.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "school")
public class SchoolProperties {

    private List<Integer> studentIds;
    private List<String> studentNames;
    private List<String> classIds;
    private List<Map<String, String>> studentOfClass;

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }

    public List<String> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<String> classIds) {
        this.classIds = classIds;
    }

    public List<Map<String, String>> getStudentOfClass() {
        return studentOfClass;
    }

    public void setStudentOfClass(List<Map<String, String>> studentOfClass) {
        this.studentOfClass = studentOfClass;
    }
}
