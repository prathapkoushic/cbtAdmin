package com.ttipl.pojo;

import java.sql.Time;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "exam_loc_session")
public class ExamLocationSess {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_loc_session_id", nullable = false, unique = true)
	private int id;
	@Column(name = "examId")
	private int examId;
	@Column(name = "location_name")
	private String location_name;

	@Column(name = "startTime")
	private Time start_time;

	@Column(name = "endTime")
	private Time end_time;

	@Column(name = "address", columnDefinition = "longtext")
	private String address;
	@Column(name = "ipaddress")
	private String ipaddress;
	@Column(name = "moblie_number")
	private String mobileNumber;

	@Column(name = "session_date_time")
	private LocalDateTime date_created;
	@Transient
	private String date_created_dupli;

	@Transient
	private String date;
	@Column(name = "ContactName")
	private String contactName;

	private String user_name;
	private String password;

	@Column(name = "exam_start_flag", nullable = false, columnDefinition = "BIT", length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean exaStartFlag;
	private String exam_start_flag_updated_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate_created_dupli() {
		return date_created_dupli;
	}

	public void setDate_created_dupli(String date_created_dupli) {
		LocalDateTime time = LocalDateTime.parse(date_created_dupli);
		this.date_created_dupli = date_created_dupli;
		setDate_created(time);
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDateTime getDate_created() {
		return date_created;
	}

	public void setDate_created(LocalDateTime date_created) {
		this.date_created = date_created;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isExaStartFlag() {
		return exaStartFlag;
	}

	public void setExaStartFlag(boolean exaStartFlag) {
		this.exaStartFlag = exaStartFlag;
	}

	public String getExam_start_flag_updated_time() {
		return exam_start_flag_updated_time;
	}

	public void setExam_start_flag_updated_time(String exam_start_flag_updated_time) {
		this.exam_start_flag_updated_time = exam_start_flag_updated_time;
	}

}
