package com.ttipl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttipl.pojo.ReportBean;
import com.ttipl.pojo.Result;

@Component
public class ResultManager {

	@Autowired
	private CandidateQueAnsService service;

	public ArrayList<ReportBean> getDetailedResults(String exam_loc_session_id, String examId) {

		String marks_per_question = "0";
		String neg_marks = "0", totalQuestions = "";
		ReportBean bean = null, temp = null;
		String response = "";
		double marks_q = 0;
		double marks_n = 0;
		int attempted = 0;
		int correct = 0;
		double totalMarks = 0;
		double totalNegMarks = 0;
		String attempted1 = "", correct1 = "";
		
		List<Object[]> objects_candidate = service.findCandidatesByExamSessionIdAndComminuty(exam_loc_session_id,examId);
		System.out.println("SIZE "+objects_candidate.size());
		ArrayList<ReportBean> list = new ArrayList<ReportBean>();
		for (Object[] object : objects_candidate) {
			bean = new ReportBean();
			bean.setHTnumber((String) object[0]);
			bean.setCandidateName((String) object[1]);
			if (object[2] != null) {
				bean.setDob((String) object[2].toString());
			} else {
				bean.setDob("-------");
			}
			bean.setCommunity((String) object[3]);
			bean.setExam_code((String) object[4]);
			bean.setLocationName((String) object[5]);
			bean.setPost_category((String) object[6]);
			bean.setExam_loc_id((Integer) object[8]);
			list.add(bean);
		}

		Result r = null;
		List<Object[]> candidate_result_objects = service
				.findCandidateResultByExamSessionIdAndComminuty(exam_loc_session_id, examId);
		for (Object[] objects : candidate_result_objects) 
		{
			r = new Result();
			r.setAnswer_option_id((String) objects[2].toString());
			r.setCandidate_id((String) objects[0].toString());
			r.setIs_answer(objects[3].toString().equalsIgnoreCase("1"));
			r.setQuestionId((String) objects[1].toString());
			marks_per_question = (String) objects[4].toString();
			neg_marks = (String) objects[5].toString();
			updateResultList(list, r);
		}
		for (int i = 0; i < list.size(); i++) {
			temp = list.get(i);
			marks_q = Double.parseDouble(marks_per_question);
			marks_n = Double.parseDouble(neg_marks);
			attempted1 = temp.getTotalAnswered();
			if (attempted1 == null)
				attempted1 = "0";
			attempted = Integer.parseInt(attempted1);
			correct1 = temp.getTotalCorrect();
			totalQuestions = temp.getTotalQuestions();
			if (totalQuestions == null) {
				temp.setTotalQuestions("0");
			}
			if (correct1 == null)
				correct1 = "0";
			correct = Integer.parseInt(correct1);
			totalNegMarks = ((attempted - correct) * marks_n);
			totalMarks = (correct * marks_q) - (totalNegMarks);
			temp.setNegativeMarks(totalNegMarks + "");
			temp.setTotalObtained(totalMarks + "");
			temp.setRounded_marks((int) Math.round(totalMarks));
			temp.setTotalAnswered(attempted1);
			temp.setTotalCorrect(correct1);

			/*
			 * pstmt.setString(1, attempted+""); pstmt.setString(2, correct+"");
			 * pstmt.setString(3, totalMarks+""); pstmt.setString(4, temp.getH_T_No());
			 * pstmt.executeUpdate();
			 */
			/*
			 * response = temp.getResponses(); if(response!=null &&
			 * response.lastIndexOf(",")!=-1) response =
			 * response.substring(response.lastIndexOf(",")-1); temp.setResponses(response);
			 */
		}

		return list;
	}

	public void updateResultList(List<ReportBean> list, Result r) {
		ReportBean temp = null, rb = null;
		String attempted = null, total_correct = null, total_neg = null, total_marks = null, totalQuestions = null;
		String response = "";
		for (int i = 0; i < list.size(); i++) 
		{
			temp = list.get(i);
			if (temp.getHTnumber().equalsIgnoreCase(r.getCandidate_id())) 
			{
				rb = temp;
				break;
			}
		}
		if (rb != null) 
		{

			attempted = rb.getTotalAnswered();
			total_correct = rb.getTotalCorrect();
			total_neg = rb.getNegativeMarks();
			total_marks = rb.getTotalObtained();
			response = rb.getResponses();
			totalQuestions = rb.getTotalQuestions();
			int q_count = 0;
			if (totalQuestions == null) {
				rb.setTotalQuestions("1");
			} else {
				q_count = Integer.parseInt(totalQuestions);
				rb.setTotalQuestions((q_count + 1) + "");
			}

			if (response == null) {
				response = "";
			}

			int a_count = 0, c_count = 0;
			if (!r.getAnswer_option_id().equalsIgnoreCase("0")) 
			{
				if (attempted == null) 
				{
					attempted = "0";
				}
				a_count = Integer.parseInt(attempted);
				rb.setTotalAnswered((a_count + 1) + "");
			}
			if (r.isIs_answer()) {
				if (total_correct == null) {
					total_correct = "0";
				}
				c_count = Integer.parseInt(total_correct);
				rb.setTotalCorrect((c_count + 1) + "");
			}
			if (response.length() == 0)
				response = r.getAnswer_option_id();
			else
				response = response + ',' + r.getAnswer_option_id();

			rb.setResponses(response);
		}
	}

}
