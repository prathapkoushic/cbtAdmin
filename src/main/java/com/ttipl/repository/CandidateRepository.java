package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Candidate;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
	@Query(value = "select c from Candidate c where c.exam_loc_session_id=?1")
	List<Candidate> findBylocationName(int id);
	
	@Query(value = "select c.candidate_id , c.candidate_first_name from Candidate c where c.exam_loc_session_id=?1")
	List<Object[]> findBylocationName1(int id);

	@Query(value = "select c from Candidate c where c.candidate_id=?1")
	Candidate findBycandidateId(String id);

	@Query("SELECT c.candidate_id , c.candidate_first_name , e.exam_name , s.location_name , p.post_name FROM Candidate c INNER JOIN com.ttipl.pojo.ExamLocationSess s ON c.exam_loc_session_id = s.id INNER JOIN com.ttipl.pojo.ExamBean e ON c.exam_id = e.id INNER JOIN com.ttipl.pojo.Post p ON e.postId = p.postId")
	List<Object[]> getSomething();

	@Query("SELECT c.candidate_id , c.candidate_first_name , e.exam_name , s.location_name , p.post_name FROM Candidate c INNER JOIN com.ttipl.pojo.ExamLocationSess s ON c.exam_loc_session_id = s.id INNER JOIN com.ttipl.pojo.ExamBean e ON c.exam_id = e.id INNER JOIN com.ttipl.pojo.Post p ON e.postId = p.postId where e.id=?1 and s.id=?2")
	List<Object[]> getCandidateByExam_idAndExamSession_Id(int examId, int sessionId);

	@Query("SELECT c FROM Candidate c where c.candidate_id=?1")
	Candidate findById(String id);

	@Modifying
	@Transactional
	@Query("DELETE  FROM Candidate c where c.candidate_id=?1")
	int deleteCandRecord(String id);

	@Modifying
	@Transactional
	@Query("DELETE  FROM Candidate c where c.exam_loc_session_id=?1")
	int deleteCandRecordsByExamLoc_session(int id);

	@Query("SELECT count(Distinct c.candidate_id) FROM Candidate c")
	int findDistinctByCandidateId();

	@Query(value = "select count(candidate_id) from candidate where exam_loc_session_id in (select exam_loc_session_id from exam_loc_session e where  e.start_time like ?1 and e.end_time like ?2)  and exam_id like ?3", nativeQuery = true)
	int findDistinctByCandidateId(String start_time, String end_time,String examid);
	
	
}