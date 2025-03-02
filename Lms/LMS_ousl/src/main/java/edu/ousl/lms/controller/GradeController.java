package edu.ousl.lms.controller;

import edu.ousl.lms.model.Grade;
import edu.ousl.lms.service.MLService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final MLService mlService = new MLService();

    @PostMapping("/predict")
    public String predictGrade(@RequestBody Grade studentMarks) {
        System.out.println(studentMarks);
        return mlService.getPredictedGrade(
                studentMarks.getExamMark(),
                studentMarks.getLabMark(),
                studentMarks.getAssignmentMark(),
                studentMarks.getAttendance()
        );
    }
}

