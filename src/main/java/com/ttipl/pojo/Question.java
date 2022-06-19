package com.ttipl.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ttil.encryption.EncryptionDecryption;
import com.ttipl.util.Utility;

@Entity
@Table(name = "Question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int questionId;
	@Column(name = "exam_id", nullable = false)
	private int examId;
	@Column(name = "question_paper_code")
	private String setNo;
	@Column(name = "question_type")
	private String questionType;

	@Column(name = "question", length = 65535, columnDefinition = "LONGTEXT")
	private String questionEnglish;
	@Column(name = "question_telugu", length = 65535, columnDefinition = "LONGTEXT")
	private String questionTelugu;
	@Column(name = "question_urdu", length = 65535, columnDefinition = "LONGTEXT")
	private String questionUrdu;
	@Column(name = "question_tamil", length = 65535, columnDefinition = "LONGTEXT")
	private String questionTamil;
	@Column(name = "question_marathi", length = 65535, columnDefinition = "LONGTEXT")
	private String questionMarathi;
	@Column(name = "question_kanada", length = 65535, columnDefinition = "LONGTEXT")
	private String questionKanada;
	@Column(name = "question_hindi", length = 65535, columnDefinition = "LONGTEXT")
	private String questionHindi;

	@OneToMany(mappedBy = "Question", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Options> options;
	@Column(name = "status", nullable = false, columnDefinition = "TINYINT", length = 4)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean status;
	@Column(name = "question_no")
	private int questionNo;
	@Column(name = "english_image_id")
	private Integer englishImageId = null;
	@Column(name = "hindi_image_id")
	private Integer hindiImageId = null;
	@Column(name = "urdu_image_id")
	private Integer urduImageId = null;
	@Column(name = "tamil_image_id")
	private Integer tamilImageId = null;
	@Column(name = "marathi_image_id")
	private Integer marathiImageId = null;
	@Column(name = "telugu_image_id")
	private Integer teluguImageId = null;
	@Column(name = "kanada_image_id")
	private Integer kanadaImageId = null;

	@Column(name = "exam_loc_session_id", nullable = false)
	private int examLocSessionId;

	@Column(name = "bank_question_id")
	private int bankQuestionId;

	@Transient
	private String blob_hindi;
	@Transient
	private String blob_english;
	@Transient
	private String blob_telugu;
	@Transient
	private String blob_marathi;
	@Transient
	private String blob_kanada;
	@Transient
	private String blob_tamil;
	@Transient
	private String blob_urdu;

	public String getBlob_hindi() {
		return blob_hindi;
	}

	public void setBlob_hindi(String blob_hindi) {
		this.blob_hindi = blob_hindi;
	}

	public String getBlob_english() {
		return blob_english;
	}

	public void setBlob_english(String blob_english) {
		this.blob_english = blob_english;
	}

	public String getBlob_telugu() {
		return blob_telugu;
	}

	public void setBlob_telugu(String blob_telugu) {
		this.blob_telugu = blob_telugu;
	}

	public String getBlob_marathi() {
		return blob_marathi;
	}

	public void setBlob_marathi(String blob_marathi) {
		this.blob_marathi = blob_marathi;
	}

	public String getBlob_kanada() {
		return blob_kanada;
	}

	public void setBlob_kanada(String blob_kanada) {
		this.blob_kanada = blob_kanada;
	}

	public String getBlob_tamil() {
		return blob_tamil;
	}

	public void setBlob_tamil(String blob_tamil) {
		this.blob_tamil = blob_tamil;
	}

	public String getBlob_urdu() {
		return blob_urdu;
	}

	public void setBlob_urdu(String blob_urdu) {
		this.blob_urdu = blob_urdu;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
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

	public String getQuestionTelugu() {
		try {
			questionTelugu = EncryptionDecryption.decrypt(questionTelugu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionTelugu;
	}

	public void setQuestionTelugu(String questionTelugu) 
	{

		try {
			this.questionTelugu = EncryptionDecryption.encrypt(questionTelugu);
		} catch (Exception e) {
			this.questionTelugu = questionTelugu;
			System.out.println(e.getMessage());
		}

	}

	public String getQuestionUrdu() {
		try {
			questionUrdu = EncryptionDecryption.decrypt(questionUrdu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionUrdu;
	}

	public void setQuestionUrdu(String questionUrdu) {
		try {
			this.questionUrdu = EncryptionDecryption.encrypt(questionUrdu);
		} catch (Exception e) {
			this.questionUrdu = questionUrdu;
			System.out.println(e.getMessage());
		}
	}

	public String getQuestionTamil() {
		try {
			questionTamil = EncryptionDecryption.decrypt(questionTamil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionTamil;
	}

	public void setQuestionTamil(String questionTamil) {
		try {
			this.questionTamil = EncryptionDecryption.encrypt(questionTamil);
		} catch (Exception e) {
			this.questionTamil = questionTamil;
			System.out.println(e.getMessage());
		}
	}

	public String getQuestionMarathi() {
		try {
			questionMarathi = EncryptionDecryption.decrypt(questionMarathi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionMarathi;
	}

	public void setQuestionMarathi(String questionMarathi) {

		try {
			this.questionMarathi = EncryptionDecryption.encrypt(questionMarathi);
		} catch (Exception e) {
			this.questionMarathi = questionMarathi;
			System.out.println(e.getMessage());
		}
	}

	public String getQuestionKanada() {
		try {
			questionKanada = EncryptionDecryption.decrypt(questionKanada);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionKanada;
	}

	public void setQuestionKanada(String questionKanada) {
		try {
			this.questionKanada = EncryptionDecryption.encrypt(questionKanada);
		} catch (Exception e) {
			this.questionKanada = questionKanada;
			System.out.println(e.getMessage());
		}
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

	public int getBankQuestionId() {
		return bankQuestionId;
	}

	public void setBankQuestionId(int bankQuestionId) {
		this.bankQuestionId = bankQuestionId;
	}

	public String getQuestionEnglish() {
		return questionEnglish;
	}

	public void setQuestionEnglish(String questionEnglish) {
		this.questionEnglish = questionEnglish;
	}

	public Integer getUrduImageId() {
		return urduImageId;
	}

	public void setUrduImageId(Integer urduImageId) {
		this.urduImageId = urduImageId;
	}

	public Integer getTamilImageId() {
		return tamilImageId;
	}

	public void setTamilImageId(Integer tamilImageId) {
		this.tamilImageId = tamilImageId;
	}

	public Integer getMarathiImageId() {
		return marathiImageId;
	}

	public void setMarathiImageId(Integer marathiImageId) {
		this.marathiImageId = marathiImageId;
	}

	public Integer getTeluguImageId() {
		return teluguImageId;
	}

	public void setTeluguImageId(Integer teluguImageId) {
		this.teluguImageId = teluguImageId;
	}

	public Integer getKanadaImageId() {
		return kanadaImageId;
	}

	public void setKanadaImageId(Integer kanadaImageId) {
		this.kanadaImageId = kanadaImageId;
	}

}
