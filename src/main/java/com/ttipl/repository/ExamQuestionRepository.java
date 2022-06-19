package com.ttipl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.ExamQuestions;

@Repository
public interface ExamQuestionRepository extends CrudRepository<ExamQuestions,Integer>
{

	@Query(value="select * from `exam_questions` where exam_id=?1" , nativeQuery = true)
	ExamQuestions findExamQuestionsByExamid(int exam_id);

}
