package com.example.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author rahul
 */
@Getter
@Setter
public class CreateStudentRequest {

    @NotNull(message = "First name is required")
    private String firstName;

    private String lastName;

    private String email;

    private String street;

    private String city;
}
