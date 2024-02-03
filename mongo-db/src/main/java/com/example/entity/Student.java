package com.example.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author rahul
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "student")
public class Student {

    @Id
    private String id;

    private String name;

    @Field(name = "mail")
    private String email;

    @DBRef//TODO: remove annotation to disable Reference
    private Department department;

    //@DBRef//TODO: remove annotation to disable Reference
    private List<Subject> subjects;

    @Transient
    private double percentage;

    @PersistenceCreator
    public Student(String id, String name, String email, Department department, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.subjects = subjects;
    }

    public double getPercentage() {
        if (!CollectionUtils.isEmpty(subjects)) {
            var total = subjects.stream().mapToInt(Subject::getMarksObtained).sum();
            return (double) total / subjects.size();
        }
        return 0.00;
    }
}
