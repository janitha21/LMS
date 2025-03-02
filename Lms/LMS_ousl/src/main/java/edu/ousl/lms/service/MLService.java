package edu.ousl.lms.service;

import edu.ousl.lms.entity.GradeEntity;
import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.repository.GradeRepository;
import edu.ousl.lms.repository.StudentRepository;
import edu.ousl.lms.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MLService {
    private static final String ML_API_URL = "http://localhost:5000/predict";

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public String getPredictedGrade(int examMark, int labMark, int assignmentMark, int attendance,Long studentId,String  subjectId) {
        RestTemplate restTemplate = new RestTemplate();

        // Create request body
        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("exam_mark", examMark);
        requestBody.put("lab_mark", labMark);
        requestBody.put("assignment_mark", assignmentMark);
        requestBody.put("attendance", attendance);

        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(ML_API_URL, requestBody, String.class);
        System.out.println(response.getBody());

        GradeEntity gradeEntity=new GradeEntity();
        gradeEntity.setExamMark(examMark);
        gradeEntity.setLabMark(labMark);
        gradeEntity.setAssignmentMark(assignmentMark);
        gradeEntity.setAttendance(attendance);

        StudentEntity student = studentRepository.findById(studentId).orElse(null);
        SubjectEntity subject = subjectRepository.findById(subjectId).orElse(null);

        gradeEntity.setStudent(student);
        gradeEntity.setSubject(subject);
        gradeEntity.setGrade(response.getBody());

        gradeRepository.save(gradeEntity);

        return response.getBody();  // Return the response from ML API
    }


}

