package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.model.LogUser;
import edu.ousl.lms.model.Student;
import edu.ousl.lms.repository.StudentRepository;
import edu.ousl.lms.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student){

        StudentEntity studentEntity=new StudentEntity();

        studentEntity.setStudentEmail(student.getStudentEmail());
        studentEntity.setStudentName(student.getStudentName());
        studentEntity.setContactNumber(student.getContactNumber());
        studentEntity.setIdentityNumber(student.getIdentityNumber());

        studentRepository.save(studentEntity);
        return null;
    }

    @Override
    public StudentEntity checkStudentLog(LogUser logUser) {

       StudentEntity student= studentRepository.findByStudentEmail(logUser.getEmail());

       if(student == null){
           return null;
       }
       else {
          if (student.getIdentityNumber().equals(logUser.getPassword())){
              return student;

          }
       }
       return null;
    }
}
