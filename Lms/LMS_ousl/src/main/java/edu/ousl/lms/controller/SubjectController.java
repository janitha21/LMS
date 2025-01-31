package edu.ousl.lms.controller;

import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.model.Subject;
import edu.ousl.lms.service.StudentSubjectService;
import edu.ousl.lms.service.SubjectService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final StudentSubjectService studentSubjectService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get-by-id/{studentId}")
    List<SubjectEntity> getSubjectsByStudent(@PathVariable Long studentId){

       return subjectService.getSubjectsByStudent(studentId);


    }
    @PostMapping("/add")
    void addSubjectsForStudents(@RequestParam String studentEmail,
                                @RequestBody Set<String> subjectIds){

        studentSubjectService.addSubjectForStudent(studentEmail,subjectIds);

        
    }

}
