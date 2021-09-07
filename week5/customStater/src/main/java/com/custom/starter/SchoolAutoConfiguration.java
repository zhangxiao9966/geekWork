package com.custom.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnClass(School.class)
@EnableConfigurationProperties(SchoolProperties.class)
@ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
@PropertySource("classpath:application.properties")
public class SchoolAutoConfiguration {

    @Autowired
    private SchoolProperties schoolProperties;

    @Bean
    public School mySchool() {
        List<Integer> studentIds = schoolProperties.getStudentIds();
        List<String> studentNames = schoolProperties.getStudentNames();
        List<String> classIds = schoolProperties.getClassIds();

        //key:属性名 value:value
        List<Map<String, String>> studentOfClass = schoolProperties.getStudentOfClass();

        List<Student> students = new ArrayList<>(studentIds.size());
        for (int i = 0; i < studentIds.size(); i++) {
            students.add(new Student(studentIds.get(i), studentNames.get(i)));
        }

        List<Klass> klasses = new ArrayList<>();
        for (String classId : classIds) {
            klasses.add(new Klass(classId));
        }

        String classId;
        int studentId;
        for (Map info : studentOfClass) {
            for (Klass klass : klasses) {
                classId = (String) info.get("classId");
                studentId = Integer.parseInt((String) info.get("studentId"));
                if (classId.equals(klass.getId())) {
                    for (Student student : students) {
                        if (student.getId() == studentId) {
                            klass.addStudent(student);
                        }
                    }
                }
            }
        }

        return new School(klasses);
    }
}
