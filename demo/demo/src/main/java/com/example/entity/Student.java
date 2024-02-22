package com.example.entity;

import com.example.request.CreateStudentRequest;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author rahul
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Transient
    private String fullName;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_address"))
    private Address address;

    public Student(CreateStudentRequest createStudentRequest) {
        this.firstName = createStudentRequest.getFirstName();
        this.lastName = createStudentRequest.getLastName();
        this.email = createStudentRequest.getEmail();
        this.fullName = createStudentRequest.getFirstName()
                .concat(" ")
                .concat(createStudentRequest.getLastName());
    }
}
