package com.ttipl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidate_norm_results")
public class CandidateResults {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cand_norm_result_id")
	private int id;

	@Column(name = "candidate_id", unique = true, nullable = false)
	private String candidateId;
	@Column(name = "marks_obtained")
	private double marksObtained;
	@Column(name = "total_questions")
	private int totalQuestions;
	@Column(name = "total_attempted")
	private int totalAttempted;
	@Column(name = "total_correct")
	private int totalCorrect;
	@Column(name = "normalize_marks")
	private double normalizedMarks;
	@Column(name = "response")
	private String responses;
	@Column(name = "exam_loc_session_id")
	private int examLocSessionId;
	@Column(name = "total_negative_marks")
	private double totalNagetiveMarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalNagetiveMarks() {
		return totalNagetiveMarks;
	}

	public void setTotalNagetiveMarks(double totalNagetiveMarks) {
		this.totalNagetiveMarks = totalNagetiveMarks;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public double getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(double marksObtained) {
		this.marksObtained = marksObtained;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public int getTotalAttempted() {
		return totalAttempted;
	}

	public void setTotalAttempted(int totalAttempted) {
		this.totalAttempted = totalAttempted;
	}

	public int getTotalCorrect() {
		return totalCorrect;
	}

	public void setTotalCorrect(int totalCorrect) {
		this.totalCorrect = totalCorrect;
	}

	public double getNormalizedMarks() {
		return normalizedMarks;
	}

	public void setNormalizedMarks(double normalizedMarks) {
		this.normalizedMarks = normalizedMarks;
	}

	public String getResponses() {
		return responses;
	}

	public void setResponses(String responses) {
		this.responses = responses;
	}

	public int getExamLocSessionId() {
		return examLocSessionId;
	}

	public void setExamLocSessionId(int examLocSessionId) {
		this.examLocSessionId = examLocSessionId;
	}

}
