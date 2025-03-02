package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.model.LogUser;
import edu.ousl.lms.model.Student;
import edu.ousl.lms.repository.StudentRepository;
import edu.ousl.lms.service.StudentService;
import edu.ousl.lms.service.tree.Trie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private final Trie studentTrie;

    public StudentServiceImpl() {
        studentTrie = new Trie();
    }



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

    @Override
    public void loadStudentNamesIntoTrie() {

        List<StudentEntity>students=studentRepository.findAll();

        for(StudentEntity student : students){

            studentTrie.insert(student.getStudentName().toLowerCase());
        }
    }

    @Override
    public List<String> getStudentsByPrefix(String prefix) {
        return studentTrie.findStudentsWithPrefix(prefix.toLowerCase());
    }
}
