package com.ttipl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ttil.encryption.EncryptionDecryption;
import com.ttipl.util.Utility;

@Entity
@Table(name = "DescriptiveQuestion")
public class DescriptiveQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "desquestion_id")
	private int desquestion_id;
	@Column(name = "exam_id", nullable = false)
	private int examId;
	@Column(name = "question_paper_code")
	private String setNo;
	@Column(name = "question_type")
	private String questionType;
	@Column(name = "question", length = 65535, columnDefinition = "LONGTEXT")
	private String questionEnglish;
	@Column(name = "question_hindi", length = 65535, columnDefinition = "LONGTEXT")
	private String questionHindi;
	@Column(name = "status", nullable = false, columnDefinition = "TINYINT", length = 4)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean status;
	@Column(name = "question_no")
	private int questionNo;
	@Column(name = "english_image_id")
	private Integer englishImageId = null;
	@Column(name = "hindi_image_id")
	private Integer hindiImageId = null;
	@Column(name = "level")
    private String level;
	@Column(name = "exam_loc_session_id", nullable = false)
	private int examLocSessionId;

	@Transient
	private String blob_hindi;
	@Transient
	private String blob_english;


	public String getBlob_hindi() {
		return blob_hindi;
	}

	public void setBlob_hindi(String blob_hindi) {
		this.blob_hindi = blob_hindi;
	}

	public int getDesquestion_id() {
		return desquestion_id;
	}

	public void setDesquestion_id(int desquestion_id) {
		this.desquestion_id = desquestion_id;
	}

	public String getSetNo() {
		return setNo;
	}

	public void setSetNo(String setNo) {
		this.setNo = setNo;
	}

	public Integer getEnglishImageId() {
		return englishImageId;
	}

	public void setEnglishImageId(Integer englishImageId) {
		this.englishImageId = englishImageId;
	}

	public Integer getHindiImageId() {
		return hindiImageId;
	}

	public void setHindiImageId(Integer hindiImageId) {
		this.hindiImageId = hindiImageId;
	}

	public String getQuestionName() {
		try {
			questionEnglish = EncryptionDecryption.decrypt(questionEnglish);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return questionEnglish;
	}

	public void setQuestionName(String questionEnglish) {
		try {
			questionEnglish = Utility.removeTags(questionEnglish);
			this.questionEnglish = EncryptionDecryption.encrypt(questionEnglish);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	

	public String getQuestionType() {
		return questionType;
	}

	public int getExamLocSessionId() {
		return examLocSessionId;
	}

	public void setExamLocSessionId(int examLocSessionId) {
		this.examLocSessionId = examLocSessionId;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}



	public String getQuestionHindi() {
		try {
			questionHindi = EncryptionDecryption.decrypt(questionHindi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionHindi;
	}

	public void setQuestionHindi(String questionHindi) {
		try {
			questionHindi = Utility.removeTags(questionHindi);
			this.questionHindi = EncryptionDecryption.encrypt(questionHindi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}

	

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getQuestionEnglish() {
		return questionEnglish;
	}

	public void setQuestionEnglish(String questionEnglish) {
		this.questionEnglish = questionEnglish;
	}

	public String getBlob_english() {
		return blob_english;
	}

	public void setBlob_english(String blob_english) {
		this.blob_english = blob_english;
	}

	



}
