package com.example.response;

import com.example.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    private String street;

    private String city;

    private List<SubjectResponse> learningSubjects;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.fullName = student.getFirstName()
                .concat(" ")
                .concat(student.getLastName());

        if (student.getAddress() != null) {
            this.street = student.getAddress().getStreet();
            this.city = student.getAddress().getCity();
        }

        if (!CollectionUtils.isEmpty(student.getSubjects())) {
            learningSubjects = student.getSubjects()
                    .stream()
                    .map(SubjectResponse::new)
                    .toList();
        }
    }

}
