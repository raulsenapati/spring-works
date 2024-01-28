package com.example.response;

import com.example.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rahul
 */
@Getter
@Setter
public class StudentResponse {

    @JsonProperty("studentId")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String fullName;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullName = student.getFirstName()
                .concat(" ")
                .concat(student.getLastName());
    }

}
