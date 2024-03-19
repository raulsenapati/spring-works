package com.example.service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
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
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String getFirstNameById(Long id) {
        return studentRepository.findById(id)
                .get()
                .getFirstName();
    }

    public String getLastNameById(Long id) {
        return studentRepository.findById(id)
                .get()
                .getLastName();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);
        student.setAddress(address);
        Student savedStudent = studentRepository.save(student);

        List<Subject> subjectList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(createStudentRequest.getSubjectsLearning())) {
            createStudentRequest.getSubjectsLearning()
                    .forEach(s -> {
                        Subject subject = new Subject();
                        subject.setSubjectName(s.getSubjectName());
                        subject.setMarksObtained(s.getMarksObtained());
                        subject.setStudent(savedStudent);
                        subjectList.add(subject);
                    });
            subjectRepository.saveAll(subjectList);
        }

        savedStudent.setLearningSubjects(subjectList);

        return savedStudent;
    }
}
