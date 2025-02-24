package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.ChatEntity;
import edu.ousl.lms.entity.MentorEntity;
import edu.ousl.lms.entity.StudentEntity;
import edu.ousl.lms.repository.ChatRepository;
import edu.ousl.lms.repository.MentorRepository;
import edu.ousl.lms.repository.StudentRepository;
import edu.ousl.lms.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;
    private StudentRepository studentRepository;
    private MentorRepository mentorRepository;


   public Long createChat(Long senderId,Long reciverId){
       Long studentId;
       Long mentorId;

       if(senderId>reciverId){
            studentId=senderId;
            mentorId=reciverId;
       }
       else{
            studentId=reciverId;
            mentorId=senderId;
       }

       StudentEntity student=studentRepository.findById(studentId)
               .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));;
       MentorEntity mentor = mentorRepository.findById(mentorId)
               .orElseThrow(() -> new RuntimeException("mentor not found with ID: " + mentorId));;


       ChatEntity chatEntity=new ChatEntity();
       chatEntity.setStudent(student);
       chatEntity.setMentor(mentor);

       chatEntity = chatRepository.save(chatEntity);

       return chatEntity.getChatId();



   }

    @Override
    public List<ChatEntity> getChats(Long userId) {

//        if(userId>1000) {
//         return chatRepository.findAllByStudentStudentId(userId);
//
//        }
//       else{
//          return chatRepository.findAllByMentorId();
//
//        }
        return chatRepository.findAllByStudentStudentId(userId);
    }
}
