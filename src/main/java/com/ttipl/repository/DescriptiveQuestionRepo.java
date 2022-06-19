package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.DescriptiveQuestion;

@Repository
public interface DescriptiveQuestionRepo extends CrudRepository<DescriptiveQuestion , Integer>
{
	@Query(value = "select * from descriptive_question where question_paper_code=?1 and exam_id=?2 ", nativeQuery = true)
	List<DescriptiveQuestion> findCountBySetNoAndExamId(String setNo, int examId);
	
	@Query(value = "select count(*) from descriptive_question where question_paper_code=?1 and exam_id=?2 ", nativeQuery = true)
	int findCountBySetNoAndExamId2(String setNo, int examId);
	
	List<DescriptiveQuestion> findAll();

	@Modifying
	@Transactional
	@Query(value = "delete from descriptive_question_images where image_id in(select english_image_id from descriptive_question where exam_id=?1)", nativeQuery = true)
	void deleteImageByExamId1(int i);
	
	@Modifying
	@Transactional
	@Query(value = "delete from descriptive_question_images where image_id in(select hindi_image_id from descriptive_question where exam_id=?1)", nativeQuery = true)
	void deleteImageByExamId2(int i);
	
	@Modifying
	@Transactional
	@Query(value = "delete from descriptive_question where exam_id=?1 ", nativeQuery = true)
	void deleteByExamId(int i);

	@Query(value = "select * from descriptive_question where exam_id=?1 ", nativeQuery = true)
	List<DescriptiveQuestion> findByExam(int exam_id);
}
