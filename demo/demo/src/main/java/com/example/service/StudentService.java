package com.example.service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.SortBy;
import com.example.request.UpdateStudentRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    AddressRepository addressRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        var student = new Student(createStudentRequest);
        var address = addressRepository.findByCityAndStreet(createStudentRequest.getCity(), createStudentRequest.getStreet());
        if (address == null) {
            address = addressRepository.save(
                    Address.builder()
                            .street(createStudentRequest.getStreet())
                            .city(createStudentRequest.getCity())
                            .build());
        }
        student.setAddress(address);
        return studentRepository.save(student);
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        var student = studentRepository.findById(updateStudentRequest.getId())
                .get();
        if (StringUtils.isNotBlank(updateStudentRequest.getFirstName())) {
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        if (StringUtils.isNotBlank(updateStudentRequest.getLastName())) {
            student.setLastName(updateStudentRequest.getLastName());
        }
        if (StringUtils.isNotBlank(updateStudentRequest.getEmail())) {
            student.setEmail(updateStudentRequest.getEmail());
        }
        return studentRepository.save(student);
    }

    public String deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
            return STUDENT_DELETED;
        } catch (Exception e) {
            return STUDENT_NOT_EXISTS;
        }
    }

    public List<Student> getByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getByFirstNameAndLastName(String firstName, String lastName) {
        //return studentRepository.findByFirstNameAndLastName(firstName, lastName);
        return studentRepository.getByFirstNameAndLastName(firstName, lastName);
    }


    public List<Student> getByFirstNameOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }

    public List<Student> getByFirstNameIn(InQueryRequest inQueryRequest) {
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }

    public List<Student> getAllStudentsWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable)
                .getContent();
    }

    public List<Student> getAllStudentsWithSorting(SortBy direction, String property) {
        Sort sort = Sort.by(
                SortBy.DESC.equals(direction)
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC,
                //property can be multiple
                property
        );
        return studentRepository.findAll(sort);
    }

    public List<Student> like(String firstName) {
        return studentRepository.findByFirstNameContains(firstName);
    }

    public List<Student> startsWith(String firstName) {
        return studentRepository.findByFirstNameStartsWith(firstName);
    }

    public List<Student> endsWith(String firstName) {
        return studentRepository.findByFirstNameEndsWith(firstName);
    }

    public Integer updateStudentWithJpql(Long id, String firstName) {
        return studentRepository.updateFirstName(id, firstName);
    }

    public Integer deleteStudent(String firstName) {
        return studentRepository.deleteByFirstName(firstName);
    }

    public List<Student> getByCity(String city) {
        //return studentRepository.findByAddressCity(city);
        return studentRepository.getByAddressCity(city);
    }


}
