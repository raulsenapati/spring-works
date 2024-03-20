package com.example.resolver;

import com.example.enums.SubjectNameFilter;
import com.example.response.StudentResponse;
import com.example.response.SubjectResponse;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author rahul
 */
@Service
public class StudentResponseResolver implements GraphQLResolver<StudentResponse> {

    public String getFullName(StudentResponse studentResponse) {
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }

    public List<SubjectResponse> getLearningSubjects(StudentResponse studentResponse,
                                                     SubjectNameFilter subjectNameFilter) {
        var student = studentResponse.getStudent();
        if (!CollectionUtils.isEmpty(student.getLearningSubjects())) {
            return student.getLearningSubjects()
                    .stream()
                    .filter(s -> subjectNameFilter.name().equals("ALL") ||
                            subjectNameFilter.name().equalsIgnoreCase(s.getSubjectName()))
                    .map(SubjectResponse::new)
                    .toList();
        }
        return Collections.emptyList();
    }
}
