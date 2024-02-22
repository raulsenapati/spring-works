package com.example.entity;

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
@Table(name = "address", uniqueConstraints = {@UniqueConstraint(name = "unique_address", columnNames = {"street", "city"})})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address", targetEntity = Student.class)
    private Student student;

}
