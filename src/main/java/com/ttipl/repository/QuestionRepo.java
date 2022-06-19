package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.Question;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer> {

	Question findByQuestionId(int id);

	List<Question> findAll();

	@Query(value = "SELECT question_id FROM question where exam_id=?", nativeQuery = true)
	int[] findQuestionIdBasedOnExamIdAndLocationSessionId(int examId);

	@Modifying
	@Transactional
	@Query(value = "update question set exam_loc_session_id=?1 where exam_id=?2 and question_paper_code=?3", nativeQuery = true)
	int updateExamLocationSession(int locationSessionId, int examId, String setNo);

	@Modifying
	@Transactional
	@Query(value = "delete from question where exam_loc_session_id=?1 ", nativeQuery = true)
	int deleteQuestionByexamLocSessionId(int locationSessionId);

	List<Question> findBySetNoAndExamId(String setNo, int examId);
	
	@Query(value = "select count(*) from question where question_paper_code=?1 and exam_id=?2 ", nativeQuery = true)
	int findCountBySetNoAndExamId(String setNo, int examId);

	@Query(value = "select * from question where  exam_id=?1 ", nativeQuery = true)
	List<Question> findByExamId(int examId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from question where exam_id=?1 ", nativeQuery = true)
	void deleteByExamId(int i);

	@Modifying
	@Transactional
	@Query(value = "delete from question_images where image_id in(select english_image_id from question where exam_id=?1)", nativeQuery = true)
	void deleteImageByExamId1(int i);
	
	@Modifying
	@Transactional
	@Query(value = "delete from question_images where image_id in(select  hindi_image_id from question where exam_id=?1)", nativeQuery = true)
	void deleteImageByExamId2(int i);

}
