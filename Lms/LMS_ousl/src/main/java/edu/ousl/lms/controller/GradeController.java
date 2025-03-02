package edu.ousl.lms.controller;

import edu.ousl.lms.entity.GradeEntity;
import edu.ousl.lms.model.Grade;
import edu.ousl.lms.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    @Autowired
    private MLService mlService;

    @PostMapping("/predict/{subjectId}")
    public String predictGrade(@RequestBody Grade studentMarks, @RequestParam Long studentId , @PathVariable String subjectId ) {



        return mlService.getPredictedGrade(
                studentMarks.getExamMark(),
                studentMarks.getLabMark(),
                studentMarks.getAssignmentMark(),
                studentMarks.getAttendance(),
                studentId,
                subjectId

        );
    }
}

