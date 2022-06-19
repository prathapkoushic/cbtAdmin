
package com.ttipl.service;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ttil.encryption.EncryptionDecryption;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.pojo.Post;
import com.ttipl.pojo.ReportBean;

@Component
public class PDFReport {

	public String getStasticsPDFReport(ExamLocationSess examLocation, ExamBean exam, List<Object[]> objects, Post post,
			String xslPath, String pdfPath) throws Exception {
		String name = "";
		int size = 0;
		StringBuilder xmlData = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:sss");
		if (objects != null) {
			size = objects.size();
			String HTnumber = "";
			if (objects.size() != 0) {

				xmlData.append("<organization>" + "RRC" + "</organization>");
				xmlData.append("<examCode>" + exam.getExam_code() + "</examCode>");
				xmlData.append("<generatedTime>" + dateFormat.format(new Date()) + "</generatedTime>");
				xmlData.append("<postName>" + post.getPost_name() + "</postName>");

				xmlData.append("<examLocation>" + examLocation.getLocation_name() + " (" + examLocation.getStart_time()
						+ "-" + examLocation.getEnd_time() + ")" + "</examLocation>");
				xmlData.append("<totalQuestions>" + size + "</totalQuestions>");
				xmlData.append("<examDuration>" + exam.getDuration() + "</examDuration>");
				String question = "";

				for (Object[] object : objects) {
					try {
						question = EncryptionDecryption.decrypt((String) object[3]);
					} catch (Exception e) {
						question = (String) object[3];
					}
					HTnumber = (String) object[2];
					name = (String) object[0];
					xmlData.append("<list>");
					xmlData.append("<HTNumber>" + object[2] + "</HTNumber>");
					xmlData.append("<question><![CDATA[" + question + "]]></question>");
					xmlData.append("<timeAnswered>" + (object[5] == null ? "---" : object[5]) + "</timeAnswered>");
					xmlData.append("<remainingTime>" + (object[4] == null ? "---" : object[4]) + "</remainingTime>");
					xmlData.append("</list>");
				}
			}
			StringReader xml = new StringReader(
					"<row>" + "<name>" + name + "</name><HTNumber>" + HTnumber + "</HTNumber>" + xmlData + "</row>");
			System.out.println(xml.toString());

			return pdfPath + "/" + new XMLTransformer().GetTransformedString(xml, "pdf", "stastics", xslPath, pdfPath,
					"statisReports", "pdf");

		} else {
			return "";
		}
	}

	public String getCandidateQueAnsweReport(ExamLocationSess examLocation, ExamBean exam, List<Object[]> objects,
			Post post, String xslPath, String pdfPath) throws Exception {
		int answered_questions = 0;
		String answer;
		int correct_answers = 0;
		int size = 0;
		if (objects != null) {
			size = objects.size();
			if (objects.size() != 0) {
				size = objects.size();
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:sss");
		String exam_date = "---";
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
			exam_date = formatter.format(exam.getExam_date());
		} catch (Exception e) {

		}
		StringBuilder xmlData = new StringBuilder();
		xmlData.append("<organization>" + "RRC" + "</organization>");
		xmlData.append("<examCode>" + exam.getExam_code() + "</examCode>");
		xmlData.append("<generatedTime>" + dateFormat.format(new Date()) + "</generatedTime>");
		xmlData.append("<postName>" + post.getPost_name() + "</postName>");
		xmlData.append("<examDate>" + exam_date + "</examDate>");

		xmlData.append("<examLocation>" + examLocation.getLocation_name() + "</examLocation>");
		xmlData.append("<totalQuestions>" + size + "</totalQuestions>");
		String question = "";
		String HTnumber = "";
		String candidateOption = "";
		String correctOption = "";
		for (Object[] object : objects) 
		{
			answer = (String) object[5];
			if (answer.equalsIgnoreCase("N.A")) 
			{
				answered_questions++;
			}
			if (answer.equalsIgnoreCase("1")) 
			{
				correct_answers++;
			}
			try {
				question = EncryptionDecryption.decrypt((String) object[3]);
			} catch (Exception e) {
				question = (String) object[3];
			}
			try {
				candidateOption = EncryptionDecryption.decrypt((String) object[4]);
			} catch (Exception e) {
				candidateOption = (String) object[4];
			}
			try {
				correctOption = EncryptionDecryption.decrypt((String) object[6]);
			} catch (Exception e) {
				correctOption = (String) object[6];
			}
			System.out.println(correctOption);
			System.out.println(candidateOption);
			System.out.println(question);
			

			HTnumber = (String) object[2];
			xmlData.append("<list>");
			xmlData.append("<HTNumber>" + object[2] + "</HTNumber>");
			xmlData.append("<question><![CDATA[" + question + "]]></question>");
			xmlData.append("<CandidateAnswer><![CDATA[" + candidateOption + "]]></CandidateAnswer>");
			xmlData.append("<CorrectAnswer><![CDATA[" + correctOption + "]]></CorrectAnswer>");
			xmlData.append("</list>");
		}
		StringReader xml = new StringReader("<row>" + "<correct_answers>" + correct_answers + "</correct_answers>"
				+ "<TotalAttempted>" + (size - answered_questions) + "</TotalAttempted><HTNumber>" + HTnumber
				+ "</HTNumber>" + xmlData + "</row>");
		System.out.println((xml + "").toString());
		return pdfPath + "/" + new XMLTransformer().GetTransformedString(xml, "pdf", "candidateQuestionAnswerReports",
				xslPath, pdfPath, HTnumber, "pdf");
	}

	public String getCandidateResultReport(ExamLocationSess examLocation, ExamBean exam,
			List<ReportBean> reportbeanArray, Post post, String xslPath, String pdfPath) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:sss");
		String exam_date = "---";
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
			exam_date = formatter.format(exam.getExam_date());
		} catch (Exception e) {

		}
		StringBuilder xmlData = new StringBuilder();
		xmlData.append("<organization>" + "RRC" + "</organization>");
		xmlData.append("<examCode>" + exam.getExam_code() + "</examCode>");
		xmlData.append("<examDate>" + exam_date + "</examDate>");
		xmlData.append("<generatedTime>" + dateFormat.format(new Date()) + "</generatedTime>");
		xmlData.append("<postName>" + post.getPost_name() + "</postName>");

		for (ReportBean reportBean : reportbeanArray) {

			xmlData.append("<list>");
			xmlData.append("<HTNumber>" + reportBean.getHTnumber() + "</HTNumber>");
			xmlData.append("<examLocation>" + reportBean.getLocationName() + " (" + reportBean.getSession()
					+ ")</examLocation>");
			xmlData.append("<CandidateName>" + reportBean.getCandidateName() + "</CandidateName>");
			xmlData.append("<TotalQuestions>" + reportBean.getTotalQuestions() + "</TotalQuestions>");
			xmlData.append("<TotalAnswered>" + reportBean.getTotalAnswered() + "</TotalAnswered>");
			xmlData.append("<TotalCorrect>" + reportBean.getTotalCorrect() + "</TotalCorrect>");
			xmlData.append("<NegativeMarks>" + reportBean.getNegativeMarks() + "</NegativeMarks>");
			xmlData.append("<MarksObtained>" + reportBean.getTotalObtained() + "</MarksObtained>");
			xmlData.append("</list>");
		}
		StringReader xml = new StringReader("<row>" + xmlData + "</row>");
		System.out.println(xml.toString());
		return pdfPath + "/" + new XMLTransformer().GetTransformedString(xml, "pdf", "candidateResultsReport", xslPath,
				pdfPath, "CandidateQueAnsweReport", "pdf");
	}

}
