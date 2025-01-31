package edu.ousl.lms.controller;

import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.model.LogUser;
import edu.ousl.lms.model.Student;
import edu.ousl.lms.service.StudentService;
import edu.ousl.lms.service.jwt.JwtUtil;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {

    final StudentService studentService;
    @Autowired
    JwtUtil jwtUtil;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    void saveStudent(@RequestBody Student student){
        Student savedStudent=studentService.saveStudent(student);

    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/log")
    public ResponseEntity<Map<String, Object>> checkUserLogin(@RequestBody LogUser logUser){

        //if(logUser.getEmail().startsWith("s") || logUser.getEmail().startsWith("S")){

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

       // }

       //return null;
    }



}
