package edu.ousl.lms.repository;

import edu.ousl.lms.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SubjectRepository extends JpaRepository<SubjectEntity,String> {

    @Query("SELECT s FROM SubjectEntity s JOIN s.students st WHERE st.studentId = :studentId")
    List<SubjectEntity> findAllByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT s FROM SubjectEntity s WHERE s.subjectId IN :subjectIds")
    Set<SubjectEntity> findAllBySubjectIds(@Param("subjectIds") Set<String> subjectIds);

}
