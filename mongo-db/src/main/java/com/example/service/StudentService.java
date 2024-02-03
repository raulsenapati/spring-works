package com.example.service;

import com.example.entity.Student;
import com.example.repository.DepartmentRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rahul
 */
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

//    @Autowired
//    SubjectRepository subjectRepository;

    /**
     * {}
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * { "department_name" : "Computer and Information Science"}
     */
    public List<Student> byDepartmentName(String deptName) {
        //return studentRepository.findByDepartmentDepartmentName(deptName);
        var departments = departmentRepository.findByDepartmentName(deptName);
        var students = new ArrayList<Student>();
        if (!CollectionUtils.isEmpty(departments)) {
            departments
                    .forEach(
                            d -> students.addAll(studentRepository.findByDepartmentId(d.getId()))
                    );
        }
        return students;
    }
}
