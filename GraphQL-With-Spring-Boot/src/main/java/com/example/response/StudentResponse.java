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

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    private String lastName;

    private String email;

    private String street;

    private String city;

    private List<SubjectResponse> learningSubjects;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();

        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();

        if (!CollectionUtils.isEmpty(student.getLearningSubjects())) {
            this.learningSubjects = student.getLearningSubjects()
                    .stream()
                    .map(SubjectResponse::new)
                    .toList();
        }
    }
}
