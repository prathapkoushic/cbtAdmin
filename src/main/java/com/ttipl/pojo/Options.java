package com.ttipl.pojo;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "answer_option")
public class Options {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_option_id")
	private int optionId;
	
	@Column(name = "answer_option", length = 65535, columnDefinition= "LONGTEXT")
	private String answerOptionEnglish;
	
	@Column(name = "answer_option_hindi", length = 65535, columnDefinition= "LONGTEXT")
	private String answerOptionHindi;
	

	@Column(name = "answer_option_telugu", length = 65535, columnDefinition = "LONGTEXT")
	private String answerOptionTelugu;
	@Column(name = "answer_option_urdu", length = 65535, columnDefinition = "LONGTEXT")
	private String answerOptionUrdu;
	@Column(name = "answer_option_tamil", length = 65535, columnDefinition = "LONGTEXT")
	private String answerOptionTamil;
	@Column(name = "answer_option_marathi", length = 65535, columnDefinition = "LONGTEXT")
	private String answerOptionMarathi;
	@Column(name = "answer_option_kanada", length = 65535, columnDefinition = "LONGTEXT")
	private String answerOptionKanada;
	
	@JoinColumn(name = "question_id")
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Question.class)
	private Question Question;
	@Column(name = "option_order")
	private int optionOrder;
	@Column(name = "is_answer")
	private boolean answer;
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
	@Transient
	private String blob_hindi;
	@Transient
	private String blob_english;
	@Transient
	private String blob_tamil;
	@Transient
	private String blob_kanada;
	@Transient
	private String blob_telugu;
	@Transient
	private String blob_marathi;
	@Transient
	private String blob_urdu;
	

	public String  getBlob_hindi() {
		return blob_hindi;
	}

	public void setBlob_hindi(String blob_hindi) {
		this.blob_hindi = blob_hindi;
	}

	public String  getBlob_english() {
		return blob_english;
	}

	public void setBlob_english(String  blob_english) {
		this.blob_english = blob_english;
	}
	
	

	public String getBlob_tamil() {
		return blob_tamil;
	}

	public void setBlob_tamil(String blob_tamil) {
		this.blob_tamil = blob_tamil;
	}

	public String getBlob_kanada() {
		return blob_kanada;
	}

	public void setBlob_kanada(String blob_kanada) {
		this.blob_kanada = blob_kanada;
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

	public String getBlob_urdu() {
		return blob_urdu;
	}

	public void setBlob_urdu(String blob_urdu) {
		this.blob_urdu = blob_urdu;
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

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	
	

	/*
	 * public String getOption() { try { option =
	 * EncryptionDecryption.decrypt(option); } catch (Exception e) {
	 * e.printStackTrace(); } return option; }
	 * 
	 * public void setOption(String option) { try { option =
	 * Utility.removeTags(option); this.option =
	 * EncryptionDecryption.encrypt(option); } catch (Exception e) { this.option =
	 * option; e.printStackTrace(); } }
	 */

	public String getAnswerOptionEnglish() {
		return answerOptionEnglish;
	}

	public void setAnswerOptionEnglish(String answerOptionEnglish) {
		this.answerOptionEnglish = answerOptionEnglish;
	}

	public String getAnswerOptionHindi() {
		return answerOptionHindi;
	}

	public void setAnswerOptionHindi(String answerOptionHindi) {
		this.answerOptionHindi = answerOptionHindi;
	}

	public String getAnswerOptionTelugu() {
		return answerOptionTelugu;
	}

	public void setAnswerOptionTelugu(String answerOptionTelugu) {
		this.answerOptionTelugu = answerOptionTelugu;
	}

	public String getAnswerOptionUrdu() {
		return answerOptionUrdu;
	}

	public void setAnswerOptionUrdu(String answerOptionUrdu) {
		this.answerOptionUrdu = answerOptionUrdu;
	}

	public String getAnswerOptionTamil() {
		return answerOptionTamil;
	}

	public void setAnswerOptionTamil(String answerOptionTamil) {
		this.answerOptionTamil = answerOptionTamil;
	}

	public String getAnswerOptionMarathi() {
		return answerOptionMarathi;
	}

	public void setAnswerOptionMarathi(String answerOptionMarathi) {
		this.answerOptionMarathi = answerOptionMarathi;
	}

	public String getAnswerOptionKanada() {
		return answerOptionKanada;
	}

	public void setAnswerOptionKanada(String answerOptionKanada) {
		this.answerOptionKanada = answerOptionKanada;
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

	public Question getQuestion() {
		return Question;
	}

	public void setQuestion(Question question) {
		Question = question;
	}

	public int getOptionOrder() {
		return optionOrder;
	}

	public void setOptionOrder(int optionOrder) {
		this.optionOrder = optionOrder;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}
	
	

	/*
	 * public String getOptionHindi() { try {
	 * 
	 * optionHindi = EncryptionDecryption.decrypt(optionHindi); } catch (Exception
	 * e) { e.printStackTrace(); } return optionHindi; }
	 * 
	 * public void setOptionHindi(String optionHindi) {
	 * 
	 * try { optionHindi = Utility.removeTags(optionHindi); this.optionHindi =
	 * EncryptionDecryption.encrypt(optionHindi); } catch (Exception e) {
	 * this.optionHindi = optionHindi; e.printStackTrace(); }
	 * 
	 * }
	 */

}
