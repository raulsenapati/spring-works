package com.example.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author rahul
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_address"))
    private Address address;

    @OneToMany(mappedBy = "student", targetEntity = Subject.class, fetch = FetchType.EAGER)
    private List<Subject> learningSubjects;

//    public Student(CreateStudentRequest createStudentRequest) {
//        this.firstName = createStudentRequest.getFirstName();
//        this.lastName = createStudentRequest.getLastName();
//        this.email = createStudentRequest.getEmail();
//    }

}
