package com.example.repository;

import com.example.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rahul
 */
@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department findByDepartmentNameAndLocation(String deptName, String location);

    List<Department> findByDepartmentName(String deptName);
}
