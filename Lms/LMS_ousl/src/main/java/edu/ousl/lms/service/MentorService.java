package edu.ousl.lms.service;

import edu.ousl.lms.entity.MentorEntity;
import edu.ousl.lms.model.LogUser;
import edu.ousl.lms.model.Mentor;

public interface MentorService {
    Mentor saveMentor(Mentor mentor);

    MentorEntity checkMentorLog(LogUser logUser);
}
