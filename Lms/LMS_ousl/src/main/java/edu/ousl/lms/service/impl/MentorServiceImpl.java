package edu.ousl.lms.service.impl;

import edu.ousl.lms.entity.MentorEntity;
import edu.ousl.lms.model.LogUser;
import edu.ousl.lms.model.Mentor;
import edu.ousl.lms.repository.MentorRepository;
import edu.ousl.lms.service.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;


    @Override
    public Mentor saveMentor(Mentor mentor) {
        MentorEntity mentorEntity=new MentorEntity();

        mentorEntity.setMentorName(mentor.getMentorName());
        mentorEntity.setMentorEmail(mentor.getMentorEmail());
        mentorEntity.setMentorPassword(mentor.getMentorPassword());


        mentorRepository.save(mentorEntity);


        return null;
    }

    @Override
    public MentorEntity checkMentorLog(LogUser logUser) {
       MentorEntity mentor= mentorRepository.findByMentorEmail(logUser.getEmail());

       if(mentor==null){
           return null;
       }
       else{
           if(mentor.getMentorPassword().equals(logUser.getPassword())){
               return mentor;
           }
           else {
               return null;
           }
       }

    }
}
