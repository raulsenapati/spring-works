package com.example.controller;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.InQueryRequest;
import com.example.request.SortBy;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rahul
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Value("${application.name: Default Demo App}")
    private String applicationName;

    @GetMapping("/get")
    public Student getStudent() {
        //return applicationName;
        return Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .build();

    }

    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents() {
        var studentList = studentService.getAllStudents();
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @PostMapping("/create")
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        var student = studentService.createStudent(createStudentRequest);
        return new StudentResponse(student);
    }

    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest) {
        var student = studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(student);
    }

    @DeleteMapping("/delete/{id}")
    //public String deleteStudent(@RequestParam("id") Long id) {
    public String deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("/getByFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName) {
        var studentList = studentService.getByFirstName(firstName);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("/getByFirstNameAndLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        var studentList = studentService.getByFirstNameAndLastName(firstName, lastName);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("/getByFirstNameOrLastName/{firstName}/{lastName}")
    public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName, @PathVariable String lastName) {
        var studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @PostMapping("/getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest) {
        var studentList = studentService.getByFirstNameIn(inQueryRequest);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @Operation(summary = "API to get list of all students - 1 indexed")
    @GetMapping("/getAllWithPagination")
    public List<StudentResponse> getAllWithPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
        var studentList = studentService.getAllStudentsWithPagination(pageNo, pageSize);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("/getAllWithSorting")
    public List<StudentResponse> getAllWithSorting(@RequestHeader(name = "direction", required = false) SortBy direction,
                                                   @RequestParam(defaultValue = "firstName", required = false) String property) {
        var studentList = studentService.getAllStudentsWithSorting(direction, property);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("/like/{firstName}")
    public List<StudentResponse> like(@PathVariable String firstName) {
        var studentList = studentService.like(firstName);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("/startsWith/{firstName}")
    public List<StudentResponse> startsWith(@PathVariable String firstName) {
        var studentList = studentService.startsWith(firstName);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @GetMapping("/endsWith/{firstName}")
    public List<StudentResponse> endsWith(@PathVariable String firstName) {
        var studentList = studentService.endsWith(firstName);
        return studentList.stream()
                .map(StudentResponse::new)
                .toList();
    }

    @PutMapping("updateFirstName/{id}/{firstName}")
    public String updateStudentWithJpql(@PathVariable Long id, @PathVariable String firstName) {
        return studentService.updateStudentWithJpql(id, firstName) + " student(s) updated";
    }

    @DeleteMapping("deleteByFirstName/{firstName}")
    public String deleteStudentWithJpql(@PathVariable String firstName) {
        return studentService.deleteStudent(firstName) + " student(s) deleted";
    }
}
