package com.ttipl.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exam_questions")
public class ExamQuestions 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
   private Integer id;
   private int easy;
   private int medium;
   private int difficult;
   private int exam_id;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public int getEasy() {
	return easy;
}
public void setEasy(int easy) {
	this.easy = easy;
}
public int getMedium() {
	return medium;
}
public void setMedium(int medium) {
	this.medium = medium;
}
public int getDifficult() {
	return difficult;
}
public void setDifficult(int difficult) {
	this.difficult = difficult;
}
public int getExam_id() {
	return exam_id;
}
public void setExam_id(int exam_id) {
	this.exam_id = exam_id;
}
   
   
}
