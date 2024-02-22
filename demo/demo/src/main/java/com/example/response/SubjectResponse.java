package com.example.response;

import com.example.entity.Subject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author rahul
 */
@Getter
@Setter
@ToString
public class SubjectResponse {

    private Long id;

    private String subjectName;

    private Double marksObtained;

    SubjectResponse(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
        this.marksObtained = subject.getMarksObtained();
    }
}
