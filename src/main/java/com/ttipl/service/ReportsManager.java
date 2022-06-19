package com.ttipl.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ttipl.pojo.ReportBean;

@Component
public class ReportsManager 
{

	public List<ReportBean> assignObjectArrayToCandidateResultBeanarray(List<Object[]> objects)
	{
		List<ReportBean> reportBean = null;
		if (objects != null) {
			reportBean = new ArrayList<ReportBean>();

			for (Object[] object : objects) {
				ReportBean bean = new ReportBean();
				bean.setAnswer_option_id((String) (object[1] + ""));
				bean.setHTnumber((String) object[2]);
				bean.setIsAnswer((String) object[3]);
				bean.setCandidateName((String) object[4]);
				bean.setSession((String) object[5]);
				bean.setMarksPerQuestion((String) (object[6] + ""));
				bean.setNegativeMarksPerQuestion((String) (object[7] + ""));
				bean.setLocationName((String) object[8]);
				if (object.length > 8) {
					bean.setCommunity((String) object[9]);
					if (object[10] instanceof String) {
						bean.setDob((String) object[10]);
					} else if (object[10] instanceof Date) {
						bean.setDob(((Date) object[10]).toString());
					}

					bean.setResponses((String) (object[11] + ""));
					bean.setTrade((String) (object[12] + ""));
				}

				reportBean.add(bean);
			}
		}
		return getCandidateResultBeanarray(reportBean);
	}

	public LinkedHashMap<String, ArrayList<ReportBean>> LinkedHashMap(List<ReportBean> attList) {
		LinkedHashMap<String, ArrayList<ReportBean>> hashMap;
		ArrayList<String> tempList;
		hashMap = new LinkedHashMap<String, ArrayList<ReportBean>>();
		tempList = new ArrayList<String>();

		for (int i = 0; i < attList.size(); i++) {
			ReportBean exmasch = (ReportBean) attList.get(i);
			buildToHashMap(hashMap, exmasch, tempList);
		}
		return hashMap;
	}

	public void buildToHashMap(LinkedHashMap<String, ArrayList<ReportBean>> hashMap, ReportBean sa,
			ArrayList<String> tempList) {
		ArrayList<ReportBean> temp = null;
		if (tempList.contains(sa.getHTnumber())) {
			temp = (ArrayList<ReportBean>) hashMap.get(sa.getHTnumber());
		} else {
			temp = new ArrayList<ReportBean>();
		}
		temp.add(sa);
		tempList.add(sa.getHTnumber());
		hashMap.put(sa.getHTnumber(), temp);
	}

	public ArrayList<ReportBean> getCandidateResultBeanarray(List<ReportBean> attList) {

		ReportBean results, temp = null;

		LinkedHashMap<String, ArrayList<ReportBean>> map = this.LinkedHashMap(attList);
		Set<String> keys = map.keySet();
		System.out.println("keys size" + keys.size());
		ArrayList<ReportBean> beanlist = new ArrayList<ReportBean>();
		Iterator<String> listiterator = keys.iterator();
		while (listiterator.hasNext()) {
			System.out.println("in while loop");
			List<ReportBean> l = map.get(listiterator.next());
			results = new ReportBean();
			results.setTotalQuestions(String.valueOf(l.size()));
			int answered_questinos = 0;
			int correct_answers = 0;
			float marks_obtained = 0;
			int rounded_marks = 0;
			float negative = 0;
			String responses = "";
			int marks_per_question = 0;
			for (int i = 0; i < l.size(); i++) {
				temp = l.get(i);
				results.setSession(temp.getSession());
				results.setCommunity(temp.getCommunity());
				results.setDob(temp.getDob());
				results.setHTnumber(temp.getHTnumber());
				results.setCandidateName(temp.getCandidateName());
				results.setLocationName(temp.getLocationName());
				if (temp.getNegativeMarksPerQuestion() != null && temp.getMarksPerQuestion() != null) {
					negative = Float.parseFloat(temp.getNegativeMarksPerQuestion());
					marks_per_question = Integer.parseInt(temp.getMarksPerQuestion());
				}

				if (!temp.getAnswer_option_id().equalsIgnoreCase("N.A")) {
					answered_questinos++;
					if (temp.getIsAnswer().equalsIgnoreCase("1")) {
						correct_answers++;
					}
				}
				if (temp.getResponses().equalsIgnoreCase("N.A")) {
					responses = responses + "0,";
				} else {
					responses = responses + temp.getResponses() + ",";
				}
			}

			int negative_questions = answered_questinos - correct_answers;
			float f = (negative_questions * negative);
			results.setNegativeMarks(String.valueOf(f));
			marks_obtained = (correct_answers * marks_per_question) - f;
			rounded_marks = Math.round(marks_obtained);
			results.setTotalAnswered(String.valueOf(answered_questinos));
			results.setTotalCorrect(String.valueOf(correct_answers));
			results.setTotalObtained(String.valueOf(marks_obtained));
			results.setRounded_marks(rounded_marks);
			results.setResponses(responses);
			beanlist.add(results);
		}
		return beanlist;
	}

	public int getNormalaizedMarks(int no_of_correct, int marks_per_question, int no_of_negative_questions,
			int negative_mark_question) {
		float f = (no_of_negative_questions * negative_mark_question);
		float marks_obtained = (no_of_correct * marks_per_question) - f;
		return Math.round(marks_obtained);

	}

	public List<ReportBean> doObjectMarksTObean(List<Object[]> beans) {
		List<ReportBean> list = new ArrayList<ReportBean>();
		ReportBean report = null;
		for (Object[] bean : beans) {
			report = new ReportBean();
			report.setHTnumber((String) bean[0]);
			report.setResponses((String) bean[1]);
			report.setCandidateName((String) bean[2]);
			report.setTotalAnswered((String) bean[3].toString());
			report.setTotalCorrect((String) bean[4].toString());
			report.setTotalQuestions((String) bean[5].toString());
			report.setRounded_marks((int) Math.round((Double) bean[6]));
			report.setTotalObtained((String) bean[7].toString());
			report.setLocationName((String) bean[8].toString());
			report.setSession((String) bean[9].toString() + " - " + (String) bean[10].toString());
			report.setNegativeMarks((String) bean[11].toString());
			if (bean[12] != null) {
				report.setDob((String) bean[12].toString());
			} else {
				report.setDob("-------");
			}
			report.setCommunity((String) bean[13].toString());

			list.add(report);
		}
		return list;

	}
}
