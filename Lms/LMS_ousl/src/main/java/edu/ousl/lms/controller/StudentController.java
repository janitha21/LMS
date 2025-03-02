package edu.ousl.lms.controller;

import edu.ousl.lms.entity.MentorEntity;
import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.model.LogUser;
import edu.ousl.lms.model.Student;
import edu.ousl.lms.service.MentorService;
import edu.ousl.lms.service.StudentService;
import edu.ousl.lms.service.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

    final StudentService studentService;
    final MentorService mentorService;
    @Autowired
    JwtUtil jwtUtil;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    void saveStudent(@RequestBody Student student){
        Student savedStudent=studentService.saveStudent(student);

    }

    public void loadStudentNames() {

        studentService.loadStudentNamesIntoTrie();

    }

    @GetMapping("/search")
    public List<String> searchStudents(@RequestParam String prefix) {
        return studentService.getStudentsByPrefix(prefix);
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/log")
    public ResponseEntity<Map<String, Object>> checkUserLogin(@RequestBody LogUser logUser){

        if(logUser.getEmail().startsWith("z") || logUser.getEmail().startsWith("Z")) {
            MentorEntity mentorEntity =mentorService.checkMentorLog(logUser);

            if(mentorEntity != null){
                String token = jwtUtil.generateToken(mentorEntity.getMentorEmail(), mentorEntity.getMentorId(), mentorEntity.getMentorName());

                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("token", token);



                return ResponseEntity.ok(response);
            }
            else{
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", "error");
                errorResponse.put("message", "Invalid credentials");

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

            }

        }


        else{
              StudentEntity studentEntity=studentService.checkStudentLog(logUser);

              if(studentEntity != null){

                  String token = jwtUtil.generateToken(studentEntity.getStudentEmail(), studentEntity.getStudentId(), studentEntity.getStudentName());

                  Map<String, Object> response = new HashMap<>();
                  response.put("status", "success");
                  response.put("token", token);



                  return ResponseEntity.ok(response);

              }
              else {

                  Map<String, Object> errorResponse = new HashMap<>();
                  errorResponse.put("status", "error");
                  errorResponse.put("message", "Invalid credentials");

                  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);


              }

        }

    }



}
