package com.example.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author rahul
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@org.springframework.data.mongodb.core.mapping.Document(collation = "subject") //TODO: remove to disable Reference
public class Subject {

    //@org.springframework.data.annotation.Id
    //private String id; ////TODO: remove to disable Reference

    @Field(name = "subject_name")
    private String subjectName;

    @Field(name = "marks_obtained")
    private int marksObtained;
}
