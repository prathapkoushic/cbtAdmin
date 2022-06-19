package com.ttipl.pojo;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "exam")
public class ExamBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exam_id", unique = true, nullable = false)
	private int id;

	@Column(name = "exam_code")
	private String exam_code;

	@Column(name = "exam_name", unique = true)
	private String exam_name;

	@Column(name = "exam_date")
	private LocalDate exam_date;
	@Transient
	private String exam_date_dupli;
	@Transient
	private String duration_dupli;

	@Column(name = "duration")
	private Time duration;
	
	@Column(name = "descriptiveDuration")
	private Time descriptiveDuration;

	@Column(name = "no_of_questions")
	private int noOfQuestions;
	
	@Column(name = "no_of_descriptiveQuestions")
	private int no_of_descriptiveQuestions;
	
	@Column(name = "marks_per_question")
	private int marks_per_question;

	@Column(name = "negativemarks_per_question")
	private float negativemarks_per_question;

	@Column(name = "exam_description", columnDefinition = "text")
	private String exam_description;

	@Column(name = "post_id")
	private int postId;

	@Column(name = "mainIpAddress")
	private String main_ip_address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoOfQuestions() {
		return noOfQuestions;
	}

	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}

	public LocalDate getExam_date() {
		return exam_date;
	}

	public void setExam_date(LocalDate exam_date) {
		this.exam_date = exam_date;
	}

	public String getExam_code() {
		return exam_code;
	}

	public void setExam_code(String exam_code) {
		this.exam_code = exam_code;
	}

	public String getExam_name() {
		return exam_name;
	}

	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}

	public String getExam_date_dupli() {
		return exam_date_dupli;
	}

	public void setExam_date_dupli(String exam_date_dupli) {
		DateTimeFormatter fmt = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();

		LocalDate date = LocalDate.parse(exam_date_dupli, fmt);
		// LocalDateTime time = LocalDateTime.parse(exam_date_dupli + "T00:00:00");
		// (DateTimeFormatter.ISO_DATE).format(time);
		setExam_date(date);

		this.exam_date_dupli = exam_date_dupli;
	}

	public Time getDuration() {
		return duration;
	}

	public String getDuration_dupli() {
		return duration_dupli;
	}

	public void setDuration_dupli(String duration_dupli) {
		Time time = Time.valueOf(duration_dupli);
		setDuration(time);

		this.duration_dupli = duration_dupli;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public int getMarks_per_question() {
		return marks_per_question;
	}

	public void setMarks_per_question(int marks_per_question) {
		this.marks_per_question = marks_per_question;
	}

	public float getNegativemarks_per_question() {
		return negativemarks_per_question;
	}

	public void setNegativemarks_per_question(float negativemarks_per_question) {
		this.negativemarks_per_question = negativemarks_per_question;
	}

	public String getExam_description() {
		return exam_description;
	}

	public void setExam_description(String exam_description) {
		this.exam_description = exam_description;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getMain_ip_address() {
		return main_ip_address;
	}

	public void setMain_ip_address(String main_ip_address) {
		this.main_ip_address = main_ip_address;
	}

	public Time getDescriptiveDuration() {
		return descriptiveDuration;
	}

	public void setDescriptiveDuration(Time descriptiveDuration) {
		this.descriptiveDuration = descriptiveDuration;
	}

	public int getNo_of_descriptiveQuestions() {
		return no_of_descriptiveQuestions;
	}

	public void setNo_of_descriptiveQuestions(int no_of_descriptiveQuestions) {
		this.no_of_descriptiveQuestions = no_of_descriptiveQuestions;
	}
	
	

}
