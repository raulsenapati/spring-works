package com.example.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
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
@Document(collection = "department")//TODO: remove to disable Reference
public class Department {

    @Id
    private String id; //TODO: remove to disable Reference

    @Field(name = "department_name")
    private String departmentName;

    private String location;
}
