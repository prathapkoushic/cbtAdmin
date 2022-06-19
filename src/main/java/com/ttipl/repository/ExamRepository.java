package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ttipl.pojo.ExamBean;

@Repository
public interface ExamRepository extends CrudRepository<ExamBean, Integer> {

	ExamBean findById(int id);

	@Query(value = "SELECT e FROM ExamBean e")
	List<ExamBean> findExamNames();

	@Query(value = "select a.id,a.exam_code,a.exam_name,a.exam_date,a.duration,a.marks_per_question,a.negativemarks_per_question,a.descriptiveDuration,p.post_name from ExamBean  a inner join com.ttipl.pojo.Post p on a.postId=p.postId")
	List<Object[]> findAllExams();

	@Query(value = "select a.id,a.exam_code,a.exam_name,a.exam_date,a.duration,a.marks_per_question,a.negativemarks_per_question,a.exam_description,p.post_name from ExamBean  a inner join com.ttipl.pojo.Post p on a.postId=p.postId where p.postId=?1")
	List<Object[]> findAllExamsByPostId(int postId);

	@Query(value = "SELECT id FROM exam where exam_name=?", nativeQuery = true)
	int findExamId(String exam_name);

	@Query(value = "SELECT * FROM exam where post_id=?", nativeQuery = true)
	List<ExamBean> findByPostId(int postId);

	@Query(value = "SELECT post_id FROM exam where exam_id=?", nativeQuery = true)
	int getPostIdBasedOnExamId(int examId);

	@Query("SELECT u FROM ExamBean u where u.exam_name=?1")
	ExamBean gettingSand(String exName);

	@Transactional
	int deleteByPostId(int id);
}
