package edu.ousl.lms.service;

import edu.ousl.lms.entity.ContentEntity;
import edu.ousl.lms.entity.SubjectEntity;
import edu.ousl.lms.model.Content;

import java.util.List;

public interface ContentService {
    Content addContent(Content content,String subjectId);

    List<ContentEntity> getContentBySubject(String subject);
}
