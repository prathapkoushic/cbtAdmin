package com.ttipl.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
public class Candidate 
{
	@Id
	@Column(name = "candidate_id", unique = true, nullable = false)
	private String candidate_id;

	@Column(name = "exam_id")
	private int exam_id;

	@Column(name = "exam_loc_session_id")
	private int exam_loc_session_id;

	@Column(name = "post_id")
	private String post_id;

	@Column(name = "candidate_first_name")
	private String candidate_first_name;

	@Column(name = "candidate_last_name")
	private String candidate_last_name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "father_name")
	private String father_name;

	@Column(name = "dob")
	private Date dob;
	@Transient
	private String date;

	@Column(name = "community")
	private String community;

	@Column(name = "email_id")
	private String email_id;

	@Column(name = "caste")
	private String caste;

	@Column(name = "religion")
	private String religion;

	@Column(name = "district")
	private String district;

	@Column(name = "state")
	private String state;

	@Column(name = "address", length = 65535, columnDefinition = "TEXT")
	private String address;

	@Column(name = "pincode")
	private int pincode;

	@Column(name = "contact_no")
	private long contact_no;

	@Column(name = "exam_end_time")
	private Date exam_end_time;

	@Column(name = "exam_is_end", nullable = false, columnDefinition = "BIT", length = 1)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean exam_is_end;

	@Column(name = "login_ip")
	private String login_ip;

	@Column(name = "lab_location_id")
	private int lab_location_id;

	@Lob
	@Column(name = "photo", columnDefinition = "BLOB")
	private byte[] photo;

	@Column(name = "exam_code")
	private String exam_code;

	@Column(name = "exam_duration")
	private String exam_duration;

	@Column(name = "post_name")
	private String post_name;

	@Column(name = "password")
	private String password;

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public long getContact_no() {
		return contact_no;
	}

	public void setContact_no(long contact_no) {
		this.contact_no = contact_no;
	}

	public Date getExam_end_time() {
		return exam_end_time;
	}

	public void setExam_end_time(Date exam_end_time) {
		this.exam_end_time = exam_end_time;
	}

	public boolean isExam_is_end() {
		return exam_is_end;
	}

	public void setExam_is_end(boolean exam_is_end) {
		this.exam_is_end = exam_is_end;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public int getLab_location_id() {
		return lab_location_id;
	}

	public void setLab_location_id(int lab_location_id) {
		this.lab_location_id = lab_location_id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getExam_code() {
		return exam_code;
	}

	public void setExam_code(String exam_code) {
		this.exam_code = exam_code;
	}

	public String getExam_duration() {
		return exam_duration;
	}

	public void setExam_duration(String exam_duration) {
		this.exam_duration = exam_duration;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate(String date) 
	{
		try 
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			setDob(format.parse(date));
		} 
		catch (ParseException p) {}
		this.date = date;
	}

	public String getCandidate_last_name() {
		return candidate_last_name;
	}

	public void setCandidate_last_name(String candidate_last_name) {
		this.candidate_last_name = candidate_last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(String candidate_id) {
		this.candidate_id = candidate_id;
	}

	public int getExam_id() {
		return exam_id;
	}

	public String getCandidate_first_name() {
		return candidate_first_name;
	}

	public void setCandidate_first_name(String candidate_first_name) {
		this.candidate_first_name = candidate_first_name;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public int getExam_loc_session_id() {
		return exam_loc_session_id;
	}

	public void setExam_loc_session_id(int exam_loc_session_id) {
		this.exam_loc_session_id = exam_loc_session_id;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

}
