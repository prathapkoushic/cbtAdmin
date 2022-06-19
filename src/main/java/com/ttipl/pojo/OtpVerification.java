package com.ttipl.pojo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OtpVerification")
public class OtpVerification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "examId")
	private int examId;
	@Column(name = "exam_loc_session_id",unique=true,nullable=false)
	private int exam_loc_session_id;
	@Column(name = "otp")
	private String otp;
	@Column(name = "generatedTime")
	private String dateCreated;
	@Column(name = "verifiedTime")
	private String verifiedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getExam_loc_session_id() {
		return exam_loc_session_id;
	}

	public void setExam_loc_session_id(int exam_loc_session_id) {
		this.exam_loc_session_id = exam_loc_session_id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getVerifiedTime() {
		return verifiedTime;
	}

	public void setVerifiedTime(String verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

}