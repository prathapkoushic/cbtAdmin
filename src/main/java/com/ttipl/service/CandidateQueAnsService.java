package com.ttipl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttipl.pojo.CandidateResults;
import com.ttipl.repository.CandidateQueAnsRepo;
import com.ttipl.repository.CandidateResultRepo;

@Service
public class CandidateQueAnsService {
	@Autowired
	private CandidateQueAnsRepo queAnsRepo;
	@Autowired
	private CandidateResultRepo resultRepo;

	public CandidateResults saveResult(CandidateResults result) {
		return resultRepo.save(result);
	}

	public int deleteByResultByExam_isAndExamLocId(String exam_id, String exam_loc_id) {
		return resultRepo.deleteByREsultByExam_isAndExamLocId(exam_id, exam_loc_id);
	}

	public CandidateResults findbyCandidateId(String candidateId) {
		return resultRepo.findByCandidateId(candidateId);
	}

	public List<Object[]> findcandidateQuestionsReport(String id) {
		return queAnsRepo.findcandidateQuestionsReport(id);
	}

	public List<Object[]> findStatisticsReport(String id) {
		return queAnsRepo.findStatisticsReport(id);
	}

	public List<Object[]> findCandidateReportsById(String id) {
		return queAnsRepo.findCandidateReportsById(id);
	}

	public List<Object[]> findCandidateReports() {
		return queAnsRepo.findCandidateReports();
	}

	public List<Object[]> findCandidatesByExamSessionIdAndComminuty(String exam_session_id, String examId) {
		return queAnsRepo.findCandidatesByExamSessionIdAndComminuty(exam_session_id, examId);
	}

	public List<Object[]> findCandidateResultByExamSessionIdAndComminuty(String exam_session_id, String examId) {
		return queAnsRepo.findCandidateResultByExamSessionIdAndComminuty(exam_session_id, examId);
	}

	public List<Object[]> findInCandidateResultTblByCandidateId(String candidateId, int exam_session_id) {
		return queAnsRepo.findInCandidateResultTblByCandidateId(candidateId, exam_session_id);
	}

	public List<Object[]> findInCandidateResultTblByCandidateId(int exam_session_id) {
		return queAnsRepo.findInCandidateResultTblByCandidateId(exam_session_id);
	}

	public int deleteByCandidateId(String id) {
		return queAnsRepo.deleteByCandidateId(id);
	}

	public int totalCandidatesExamStarted() {
		return queAnsRepo.totalCandidatesExamStarted();
	}

	public int totalCandidatesExamStarted(String startTime, String endTime, String examId) {
		return queAnsRepo.totalCandidatesExamStarted(startTime, endTime, examId);
	}

	public List<Object[]> presentTotalCount(String startTime, String endTime, String examId) {
		return queAnsRepo.presentTotalCount(startTime, endTime, examId);
	}

	public List<Object[]> getListData() {

		return queAnsRepo.findcandidateQuestion();
	}
}
