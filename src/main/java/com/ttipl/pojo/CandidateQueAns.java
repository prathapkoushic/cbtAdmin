package com.ttipl.pojo;

import java.sql.Time;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "candidate_qa")
public class CandidateQueAns 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_qa_id")
	private int candidateQAId;
	@Column(name = "candidate_id")
	private String candidateId;
	@Column(name = "question_id")
	private int questionId;
	@Column(name = "answer_option_id")
	private int optionId;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	@Column(name = "ipaddress")
	private String ipAddress;
	@Column(name = "review", columnDefinition = "BIT", length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean review;
	@Column(name = "question_no")
	private int questionNo;
	@Column(name = "remaining_time")
	private Time remainingTime;

	public int getCandidateQAId() {
		return candidateQAId;
	}

	public void setCandidateQAId(int candidateQAId) {
		this.candidateQAId = candidateQAId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isReview() {
		return review;
	}

	public void setReview(boolean review) {
		this.review = review;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	public Time getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(Time remainingTime) {
		this.remainingTime = remainingTime;
	}

}
