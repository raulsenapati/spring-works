package com.example.repository;

import com.example.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rahul
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
