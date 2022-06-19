package com.ttipl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.CandidateResults;

@Repository
public interface CandidateResultRepo extends CrudRepository<CandidateResults, Integer> {

	CandidateResults findByCandidateId(String id);

	@Transactional
	@Modifying
	@Query(value = "delete from candidate_norm_results where candidate_id in (select candidate_id from candidate where exam_id like ?1 and exam_loc_session_id like ?2)", nativeQuery = true)
	int deleteByREsultByExam_isAndExamLocId(String examId, String examLocId);
}
