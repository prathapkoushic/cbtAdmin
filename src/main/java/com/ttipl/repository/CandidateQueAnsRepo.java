package com.ttipl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ttipl.pojo.CandidateQueAns;

@Repository
public interface CandidateQueAnsRepo extends CrudRepository<CandidateQueAns, Integer> 
{
	@Query(value = "select concat(c.candidate_first_name,c.candidate_last_name)as candidate_name,c.dob,c.candidate_id,q.question, "
			+ " IFNULL(ao.answer_option,'N.A')as answer_option,case when ao.is_answer =0 then '0'  when ao.is_answer =1 then '1' else 'N.A' end as is_answer,IFNULL(ao1.ANSWER_OPTION,'N.A') as correctanswer "
			+ " from candidate_qa cqa " + " left join question q on q.question_id=cqa.question_id "
			+ " left join answer_option ao1 on ao1.question_id=cqa.question_id and ao1.is_answer=1 "
			+ " left join answer_option ao on ao.answer_option_id=cqa.answer_option_id "
			+ " inner join candidate c on c.candidate_id=cqa.candidate_id "
			+ " where c.candidate_id=?1", nativeQuery = true)
	public List<Object[]> findcandidateQuestionsReport(String id);

	@Query(value = "select concat(c.candidate_first_name,c.candidate_last_name)as candidate_name,c.dob,c.candidate_id,q.question,cqa.remaining_time,cqa.date_time\r\n"
			+ "			 " + "			from candidate_qa cqa  left join question q on q.question_id=cqa.question_id "
			+ "			" + "			 inner join candidate c on c.candidate_id=cqa.candidate_id"
			+ "			where c.candidate_id=?1", nativeQuery = true)
	public List<Object[]> findStatisticsReport(String id);

	@Query(value = " select cqa.question_id,IFNULL(cqa.answer_option_id,'N.A')as answer_option_id,\r\n"
			+ "cqa.candidate_id,case when ao.is_answer =0 then '0'  when ao.is_answer =1 then '1' else 'N.A' end as is_answer,\r\n"
			+ "concat(c.candidate_first_name,'',c.candidate_last_name)as candidate_name,concat(els.start_time,'-',els.end_time) as session, "
			+ "e.marks_per_question,e.negativemarks_per_question,IFNULL(els.location_name,'N.A') as location_name ,c.community,c.dob, ifnull(ao.option_order ,'N.A') as option_answer ,c.exam_id   from candidate_qa cqa  "
			+ "left join answer_option ao on cqa.answer_option_id=ao.answer_option_id "
			+ "inner join candidate c on c.candidate_id=cqa.candidate_id "
			+ "left join question q on q.question_id=cqa.question_id "
			+ "inner join exam_loc_session els on els.exam_loc_session_id=c.exam_loc_session_id "
			+ "inner join exam e on e.exam_id=els.exam_id ", nativeQuery = true)
	public List<Object[]> findCandidateReports();

	@Query(value = " select cqa.question_id,IFNULL(cqa.answer_option_id,'N.A')as answer_option_id,"
			+ "cqa.candidate_id,case when ao.is_answer =0 then '0'  when ao.is_answer =1 then '1' else 'N.A' end as is_answer,"
			+ "concat(c.candidate_first_name,'',c.candidate_last_name)as candidate_name,concat(els.start_time,'-',els.end_time) as session,  "
			+ "e.marks_per_question,e.negativemarks_per_question,IFNULL(els.location_name,'N.A') as location_name  ,c.community,c.dob, ifnull(ao.option_order ,'N.A') as option_answer ,c.exam_id    from candidate_qa cqa "
			+ "left join answer_option ao on cqa.answer_option_id=ao.answer_option_id "
			+ "inner join candidate c on c.candidate_id=cqa.candidate_id "
			+ "left join question q on q.question_id=cqa.question_id "
			+ "inner join exam_loc_session els on els.exam_loc_session_id=c.exam_loc_session_id "
			+ "inner join exam e on e.exam_id=els.exam_id where cqa.candidate_id= ?1", nativeQuery = true)
	public List<Object[]> findCandidateReportsById(String id);

	@Query(value = "select c.candidate_id,c.candidate_first_name as candidate_name,\r\n"
			+ "			c.dob,ifnull(c.community,'N.A') as community,e.exam_code,concat(els.location_name,' (',els.session_date_time,')') as loaction_session\r\n"
			+ "	,p.post_name,pc.post_category_name	,els.exam_loc_session_id	from candidate c, exam e, exam_loc_session els,post p,post_category pc where e.post_id=p.post_id and pc.post_category_id=p.post_category_id    and c.exam_loc_session_id= els.exam_loc_session_id\r\n"
			+ "			and e.exam_id=els.exam_id and els.exam_loc_session_id like ?1 and e.exam_id like ?2   order by (c.candidate_id+0)\r\n", nativeQuery = true)
	public List<Object[]> findCandidatesByExamSessionIdAndComminuty(String exam_session_id, String examId);

	@Query(value = "select cq.candidate_id,cq.question_id,ifnull(ap.option_order,0) as answer_option,case when ap.is_answer =0 then '0'  when ap.is_answer =1 then '1' else 'N.A' end as is_answer,\r\n"
			+ " e.marks_per_question, e.negativemarks_per_question \r\n" + " from candidate_qa cq \r\n"
			+ " inner join question q on q.question_id=cq.question_id \r\n"
			+ " inner join exam_loc_session els on els.exam_loc_session_id=q.exam_loc_session_id \r\n"
			+ " left join answer_option as ap on ap.answer_option_id=cq.answer_option_id \r\n"
			+ " inner join exam e on e.exam_id= els.exam_id \r\n"
			+ " inner join candidate c on c.candidate_id= cq.candidate_id \r\n"
			+ "where c.exam_loc_session_id like ?1 and  c.exam_id like ?2 ", nativeQuery = true)
	public List<Object[]> findCandidateResultByExamSessionIdAndComminuty(String exam_session_id, String examId);

	@Query(value = "SELECT cnr.candidate_id,cnr.response,concat(c.candidate_first_name) as full_name,cnr.total_attempted,cnr.total_correct,cnr.total_questions,cnr.normalize_marks,cnr.marks_obtained,els.location_name,els.start_time,els.end_time ,cnr.total_negative_marks ,c.dob,c.caste  FROM candidate_norm_results cnr inner join candidate c on c.candidate_id=cnr.candidate_id inner join\r\n"
			+ " exam_loc_session els on els.exam_loc_session_id =cnr.exam_loc_session_id where cnr.exam_loc_session_id=?1 ", nativeQuery = true)
	public List<Object[]> findInCandidateResultTblByCandidateId(int exam_loc_session_id);

	@Query(value = "SELECT cnr.candidate_id,cnr.response,concat(c.candidate_first_name,' ',c.candidate_last_name) as full_name,cnr.total_attempted,cnr.total_correct,cnr.total_questions,cnr.normalize_marks,cnr.marks_obtained,els.location_name,els.start_time,els.end_time ,cnr.total_negative_marks ,c.dob,c.caste FROM candidate_norm_results cnr inner join candidate c on c.candidate_id=cnr.candidate_id inner join\r\n"
			+ " exam_loc_session els on els.exam_loc_session_id =cnr.exam_loc_session_id where cnr.candidate_id=?1 and cnr.exam_loc_session_id=?2 ", nativeQuery = true)
	public List<Object[]> findInCandidateResultTblByCandidateId(String candidateId, int exam_loc_session_id);

	@Transactional
	@Modifying
	@Query(value = " delete from candidate_qa where candidate_id=?", nativeQuery = true)
	public int deleteByCandidateId(String id);

	@Query(value = "SELECT count(distinct candidate_id) FROM candidate_qa where candidate_id in (select candidate_id from candidate where exam_loc_session_id in (select exam_loc_session_id from exam_loc_session e where  e.start_time like ?1 and e.end_time like ?2)  and exam_id like ?3)", nativeQuery = true)
	public int totalCandidatesExamStarted(String startTime, String endTime, String examId);

	@Query(value = "SELECT count(distinct candidate_id) FROM candidate_qa", nativeQuery = true)
	public int totalCandidatesExamStarted();

	@Query(value = "select e.start_time,e.end_time, e.location_name ,e.address,(select count(*)  from candidate c where c.exam_loc_session_id =e.exam_loc_session_id) as total,\r\n"
			+ "(SELECT count(distinct cqa.candidate_id) FROM candidate_qa cqa inner join candidate ce on  ce.candidate_id=cqa.candidate_id where ce.exam_loc_session_id=e.exam_loc_session_id) as present \r\n"
			+ " from exam_loc_session e  where e.start_time like ?1 and e.end_time like ?2  and e.exam_id like ?3", nativeQuery = true)
	public List<Object[]> presentTotalCount(String startTime, String endTime, String examId);

	@Query(value = "select q.question,count(*),(select count(*)"
			+ " from candidate_qa cq where cq.answer_option_id is not null and cq.answer_option_id in (select answer_option_id from answer_option where is_answer=1 AND cq.question_id = cqa.question_id )) as attempted_questions from candidate_qa cqa inner join question q on cqa.question_id=q.question_id inner join exam e on q.exam_id=e.exam_id inner join exam_loc_session el on q.exam_loc_session_id=el.exam_loc_session_id where cqa.answer_option_id is not null group  by q.question_no,q.question_id,q.exam_loc_session_id", nativeQuery = true)
	public List<Object[]> findcandidateQuestion();

}
