package com.ttipl.pojo;

public class Result {
	private String questionId;
	private String answer_option_id;
	private String candidate_id;
	private boolean is_answer;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswer_option_id() {
		return answer_option_id;
	}

	public void setAnswer_option_id(String answer_option_id) {
		this.answer_option_id = answer_option_id;
	}

	public String getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(String candidate_id) {
		this.candidate_id = candidate_id;
	}

	public boolean isIs_answer() {
		return is_answer;
	}

	public void setIs_answer(boolean is_answer) {
		this.is_answer = is_answer;
	}

}
