package com.example.service;

import com.example.entity.Student;
import com.example.repository.DepartmentRepository;
import com.example.repository.StudentRepository;
import com.example.request.SortBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rahul
 */
@Service
public class StudentService {

    private static final String STUDENT_DELETED = "Student has been deleted successfully";

    private static final String STUDENT_NOT_EXISTS = "Student probably does not exist";
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

//    @Autowired
//    SubjectRepository subjectRepository;

    public Student createStudent(Student student) {
        if (student.getDepartment() != null) {
            var department = departmentRepository.findByDepartmentNameAndLocation(
                    student.getDepartment().getDepartmentName(),
                    student.getDepartment().getLocation()
            );
            if (department != null) {
                department = departmentRepository.save(department);
            }
            student.setDepartment(department);
        }
//        if(!CollectionUtils.isEmpty(student.getSubjects())) {
//            subjectRepository.saveAll(student.getSubjects());
//        }
        return studentRepository.save(student);
    }

    /**
     * { "id" : "65a5af3409eb5e18af59850f"}
     */
    public Student getStudentById(String id) {
        var student = studentRepository.findById(id);
        if (student.isPresent())
            return student.get();
        return null;
    }

    /**
     * {}
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        if (student.getDepartment() != null) {
            var department = departmentRepository.findByDepartmentNameAndLocation(
                    student.getDepartment().getDepartmentName(),
                    student.getDepartment().getLocation()
            );
            if (department != null) {
                department = departmentRepository.save(department);
            }
            student.setDepartment(department);
        }
        return studentRepository.save(student);
    }

    public String deleteStudent(String id) {
        var student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return STUDENT_DELETED;
        }
        return STUDENT_NOT_EXISTS;
    }

    /**
     * { "name" : "Yuvraj"}
     */
    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
        //return studentRepository.getByName(name);
    }

    /**
     * { "name" : "John", "mail" : "john@gmail.com"}
     */
    public List<Student> getStudentsByNameAndMail(String name, String email) {
        return studentRepository.findByNameAndEmail(name, email);
    }

    /**
     * { "$or" : [{ "name" : "John-1"}, { "mail" : "steve@gmail.com"}]}
     */
    public List<Student> getStudentsByNameOrMail(String name, String email) {
        return studentRepository.findByNameOrEmail(name, email);
    }

    /**
     * skip(10).limit(10)
     * skip value is calculated by method
     */
    public List<Student> getAllWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable)
                .getContent();
    }

    /**
     * db.getCollection('student').find({}).sort({name: -1}) //descending
     */
    public List<Student> allWithSorting(SortBy direction, String property) {
        Sort sort = Sort.by(
                SortBy.DESC.equals(direction)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC,
                //property can be multiple
                property
        );
        return studentRepository.findAll(sort);
    }


    /**
     * { "department.department_name" : "Computer and Information Science"} // old
     * { "department_name" : "Computer and Information Science"}
     * then query-ed with department id
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

    /**
     * { "subjects.subject_name" : "Reinforcement Learning"}
     */
    public List<Student> bySubjectName(String subName) {
        return studentRepository.findBySubjectsSubjectName(subName);
    }

    /**
     * { "mail" : /yahoo/ }
     */
    public List<Student> emailLike(String email) {
        return studentRepository.findByEmailIsLike(email);
    }

    /**
     * { "name" : /^John/ }
     */
    public List<Student> nameStartsWith(String name) {
        return studentRepository.findByNameStartsWith(name);
    }

    /**
     * { "department" : { "$java" : { "$ref" : "department", "$id" : "65a5b2a30dd6a27ad7c7c6eb" } } }
     */
    public List<Student> byDepartmentId(String deptId) {
        return studentRepository.findByDepartmentId(deptId);
    }
}
