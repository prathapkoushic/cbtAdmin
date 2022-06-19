package com.ttipl.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.service.AnswerOptionService;
import com.ttipl.service.CandidateService;
import com.ttipl.service.ExamLocationService;
import com.ttipl.service.ExamService;
import com.ttipl.service.ExcelService;
import com.ttipl.service.PostCategoryService;
import com.ttipl.service.PostService;
import com.ttipl.service.QuestionService;
import com.ttipl.util.SessionHandling;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttipl.pojo.Candidate;
import com.ttipl.pojo.DescriptiveQuestion;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.pojo.ExamQuestions;
import com.ttipl.pojo.Options;
import com.ttipl.pojo.Post;
import com.ttipl.pojo.PostCategory;
import com.ttipl.pojo.Question;

@Controller
public class MainController
{

	@Autowired
	private PostService postService;
	@Autowired
	private QuestionService questionService;

	@Autowired
	private ExamService examService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private ExcelService excelService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	private SessionHandling sessionHandler;
	@Autowired
	private ExamLocationService examLocationService;
	@Autowired
	private PostCategoryService postCategoryService;
	@Autowired
	private AnswerOptionService answerOptionService;
	
	@RequestMapping("/delQ")
	public String delQs(Model model) 
	{
				
		List<ExamBean> examList = examService.findAllExams();
		model.addAttribute("values", examList);
		return  "delete";
	}
	
	@RequestMapping("/delDesQ")
	public String delDesQ(Model model) 
	{
				
		List<ExamBean> examList = examService.findAllExams();
		model.addAttribute("values", examList);
		return  "delete2";
	}
	
	
	@RequestMapping("/descriptive")
	public String descriptive(Model model) 
	{
				
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "uploadDescriptiveQuestions";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}
	
	@RequestMapping("/examQuestions")
	public String examQuestions(Model model) 
	{
				
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "randomize";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}
	
	@RequestMapping("/randomize")
	public String randomize(Model model , @ModelAttribute("ExamQuestions") ExamQuestions questions) 
	{
				
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) 
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} 
		else 
		{
			if (sessionHandler.authenticateAccess(httpSession)) 
			{
				 List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				List<DescriptiveQuestion>  list = questionService.findDesQuestions(questions.getExam_id());
				ExamBean exam = examService.findByExamId(questions.getExam_id());
				if(list.size()==0)
				{
					model.addAttribute("msg", "<span style='color:red'>"+"The Exam "+exam.getExam_name()+"  doesn't contain qustions first upload questions."+"</span>");
			    	model.addAttribute("values", examList);
			    	return "randomize";
				}
				ExamQuestions examQuestions1  = questionService.findExamQuestionsByExamid(questions.getExam_id());
				if(examQuestions1==null)
				{
			    
			    int total = questions.getEasy()+questions.getMedium()+questions.getDifficult();
			   
			    
			    if(total!=exam.getNo_of_descriptiveQuestions() )
			    {
			    	model.addAttribute("msg", "<span style='color:red'>"+"The number of questions randomized should be equal to the Total number of questions allotted for the exam"+"</span>");
			    	model.addAttribute("values", examList);
			    	return "randomize";
			    }
				ExamQuestions examQuestions = questionService.savAll(questions);
				
				
				if(examQuestions==null)
					model.addAttribute("msg", "<span style='color:red'>"+"Data was not inserted successfully"+"</span>");
				else
					model.addAttribute("msg", "<span style='color:green'>"+"Data inserted successfully"+"</span>");
				page = "randomize";
				}
				else
				{
					model.addAttribute("msg", "<span style='color:red'>"+"Data already inserted"+"</span>");
					page = "randomize";
				}
			} 
		  else 
			{
				page = "noaccess";
			}
		}
		return page;
	}
	
	@RequestMapping("/dec")
	public String dec(Model model) 
	{	
		return  "decrypt";
	}

	@RequestMapping("/delQues")
	public String delQues(Model model , @RequestParam("examId") int examId) 
	{
		
		  answerOptionService.deleteQuestions(examId);
		  questionService.deleteQuestionImages(examId);
		  questionService.deleteQuestions(examId);
		  ExamBean exam = examService.findByExamId(examId);
		  model.addAttribute("msg", "ALL QUETIONS OF EXAM '<span style='color:red'>"+exam.getExam_name().toUpperCase()+"</span>' ARE DELETED");
		  List<ExamBean> examList = examService.findAllExams();
		  model.addAttribute("values", examList);
		  return "delete";
		
	}
	
	@RequestMapping("/delDesQues")
	public String delDesQues(Model model , @RequestParam("examId") int examId) 
	{
		
		  questionService.deleteDesQuestionImages(examId);
		  questionService.deleteDesQuestions(examId);
		  ExamBean exam = examService.findByExamId(examId);
		  model.addAttribute("msg", "ALL QUETIONS OF EXAM '<span style='color:red'>"+exam.getExam_name().toUpperCase()+"</span>' ARE DELETED");
		  List<ExamBean> examList = examService.findAllExams();
		  model.addAttribute("values", examList);
		  return "delete2";
		
	}
	
	@RequestMapping("/decrypt")
	public void decrypt(Model model) throws Exception 
	{
		questionService.findAllEncQuestions();
		 
	}
	@RequestMapping("/uploadurl")
	public String uploadData(Model model) 
	{
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) 
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		}
		else
		{
			if (sessionHandler.authenticateAccess(httpSession)) 
			{
				model.addAttribute("values", examService.getResult());
				page = "uploadCandidateData";
			}
			else
			{
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/candidatesByExam")
	public String candidatesByExam(Model model) 
	{
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) 
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} 
		else 
		{
			if (sessionHandler.authenticateAccess(httpSession)) 
			{
				model.addAttribute("values", examService.getResult());
				page = "uploadCandidateByExam";
			} 
			else
				page = "noaccess";
		}
		return page;
	}

	@RequestMapping(value = "dd", method = RequestMethod.POST)
	public @ResponseBody String phoList(@RequestParam("exam_id") int exam_id) throws JsonProcessingException {
		String jsonStr = "";
		// ExamBean ex = examService.getList(String.valueOf(exam_id));
		// int id = ex.getId();
		List<Object[]> es = examLocationService.getListOfLocations(exam_id);
		ObjectMapper mapper = new ObjectMapper();
		jsonStr = mapper.writeValueAsString(es);
		return jsonStr;
	}

	@RequestMapping(value = ("/uploadcandidatedata"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public String importCode(@RequestParam("file") MultipartFile file, @RequestParam("exam_name") int exam_id,
			@RequestParam("location_name") int location_session_id, Model model,
			@RequestParam("password") String password) {
		String msg = "";
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				try {
					String filePath = file.getOriginalFilename();
					if (filePath.endsWith(".xlsx")) {
						msg = candidateService.importCodexlsx(file, exam_id, location_session_id, 10, password);
					}
				} catch (IOException e) {
					model.addAttribute("msg", "<span class=\"font-red\">500 Error</span>");
					e.printStackTrace();
				}
				model.addAttribute("msg", msg);
				model.addAttribute("values", examService.getResult());
				page = "uploadCandidateData";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping(value = ("/uploadCandidateByExam"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public String uploadCandidateByExam(@RequestParam("file") MultipartFile file, @RequestParam("examId") int exam_id,
			@RequestParam("password") String password, Model model) {
		String msg = "";
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				try {
					String filePath = file.getOriginalFilename();
					if (filePath.endsWith(".xlsx")) {
						msg = candidateService.importCodexlsx(file, exam_id, 0, 11, password);
					}
				} catch (IOException e) {
					model.addAttribute("msg", "500 Error");
					e.printStackTrace();
				}
				model.addAttribute("msg", msg);
				model.addAttribute("values", examService.getResult());
				page = "uploadCandidateByExam";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/createcandidate")
	public String candidateData(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("values", examService.getResult());
				model.addAttribute("value", examLocationService.getData());
				model.addAttribute("valuee", postService.getPostResult());

				page = "candidate";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/CandidateList")
	public String CandidateList(Model model, @RequestParam(name = "sessionId", required = false) Integer sessionId,
			@RequestParam(name = "examId", required = false) Integer examId) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				if (sessionId != null && examId != null) {
					model.addAttribute("value",
							candidateService.getCandidateByExam_idAndExamSession_Id(examId, sessionId));
				} else {
					model.addAttribute("value", candidateService.getAll());
				}
				model.addAttribute("exams", examService.getResult());
				page = "candidateList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/editCandidate")
	public String editCandidate(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) 
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} 
		else
		{
			if (sessionHandler.authenticateAccess(httpSession))
			{
				List<Object[]> values = candidateService.getAll();
				model.addAttribute("value", values);
				page = "candidateEdit";
			} 
			else
			{
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/candEdit{id}")
	public String updateCandidate(Model model, @PathVariable("id") String id) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				Candidate values = candidateService.getById(id);
				model.addAttribute("exams", examService.getResult());
				model.addAttribute("posts", postService.getPostResult());
				model.addAttribute("locations", examLocationService.getData());

				model.addAttribute("value", values);
				page = "updateCandidate";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/candDelete{id}")
	public String deleteCandidate(Model model, @PathVariable("id") String id) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				try {
					int rowAffected = candidateService.deleteByOne(id);

					if (rowAffected != 0) {
						model.addAttribute("msg", rowAffected + " record deleted successfully");
					}
				} catch (Exception e) {
					model.addAttribute("msg", "could not delete candidate at this moment!");
				}
				List<Object[]> values = candidateService.getAll();
				model.addAttribute("value", values);

				page = "candidateList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/updateCandidate{id}")
	public String updateCandidateSecond(Model model, @PathVariable("id") String id,
			@ModelAttribute("candidate") Candidate candidate) throws ParseException, SQLException {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				candidate.setCandidate_id(id);
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String dd = candidate.getDate();
				candidate.setCaste(candidate.getCommunity());
				Date dat = sf.parse(dd);
				candidate.setDob(dat);
				candidateService.saveCandidate(candidate);
				List<Object[]> values = candidateService.getAll();
				model.addAttribute("value", values);
				model.addAttribute("msg", "updated The Candidate ( " + id + " )successfully");
				page = "candidateList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping(value = "saveCandidate", method = RequestMethod.POST)
	public String saveCandidate(@ModelAttribute("candidate") Candidate candidate, Model model)
			throws ParseException, SQLException {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String dd = candidate.getDate();
				Date dat = sf.parse(dd);
				candidate.setDob(dat);
				candidateService.saveCandidate(candidate);
				model.addAttribute("msg", "candidate added success fully");
				model.addAttribute("values", examService.getResult());
				model.addAttribute("value", examLocationService.getData());
				model.addAttribute("valuee", postService.getPostResult());
				page = "candidate";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/createQuestion")
	public String addQuestionUrl(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				return "AddQuestion";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/uplaodQuestions")
	public String uplaodQuestionUrl(@RequestParam("file") MultipartFile file, @RequestParam("examId") int examId,
			@RequestParam("setNo") String setNo, @RequestParam("password") String password, Model model)
	{
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession))
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} 
		else 
		{
			if (sessionHandler.authenticateAccess(httpSession))
			{
				int totalQuestions = 0;
				int count = questionService.findCountBySetNoAndExamId(setNo, examId);
				ExamBean exam = examService.findById(examId);
				totalQuestions = exam.getNoOfQuestions();
				if (count < totalQuestions) 
				{
					try {
						int examLocSessionId = 0;
						List<ExamLocationSess> exams = examLocationService.findByExamId(examId);
						if (exams != null) 
						{
							if (exams.size() != 0) 
							{
								examLocSessionId = exams.get(0).getId();
							} 
							else 
							{
								model.addAttribute("msg",
										"<span class=\"font-red\">Exam Location Session Not Found ?</span>");
							}
						} 
						else 
						{
							model.addAttribute("msg",
									"<span class=\"font-red\">Exam Location Session Not Found ?</span>");
						}
						
						String message = excelService.ExtractQuestionsFromExcel(file, examId, setNo,totalQuestions ,totalQuestions - count,
								examLocSessionId, password);
						model.addAttribute("msg", message);
					} catch (Exception e) {
						model.addAttribute("msg", e.getMessage());
						 e.printStackTrace();
					}
				} else {
					ExamBean examBean= examService.findByExamId(examId);
					String examName = examBean.getExam_name();
					model.addAttribute("msg", " <span class=\"font-red\">Already Uploaded Questions for the Exam '<span style='color:blue'>"+ examName +"</span>'</span>");
				}
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "uploadQuestions";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	
	@RequestMapping("/uplaodDescriptiveQuestions")
	public String uplaodDescriptiveQuestions(@RequestParam("file") MultipartFile file, @RequestParam("examId") int examId,
			@RequestParam("setNo") String setNo , @RequestParam("level") String level, @RequestParam("password") String password, Model model)
	{
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession))
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} 
		else 
		{
			if (sessionHandler.authenticateAccess(httpSession))
			{
				int totalQuestions = 0;
				int count = questionService.findCountBySetNoAndExamId2(setNo, examId);
				ExamBean exam = examService.findById(examId);
				totalQuestions = exam.getNo_of_descriptiveQuestions();
				
				/*if (count < totalQuestions) 
				{*/
					try {
						int examLocSessionId = 0;
						List<ExamLocationSess> exams = examLocationService.findByExamId(examId);
						if (exams != null) 
						{
							if (exams.size() != 0) 
							{
								examLocSessionId = exams.get(0).getId();
							} 
							else 
							{
								model.addAttribute("msg",
										"<span class=\"font-red\">Exam Location Session Not Found ?</span>");
							}
						} 
						else 
						{
							model.addAttribute("msg",
									"<span class=\"font-red\">Exam Location Session Not Found ?</span>");
						}
						
						String message =  excelService.ExtractQuestionsFromExcel2(file, examId, setNo,totalQuestions ,totalQuestions - count,
								examLocSessionId, password , level);
						model.addAttribute("msg", message);
					} catch (Exception e) {
						model.addAttribute("msg", e.getMessage());
						 e.printStackTrace();
					}
				/*}
					 * else { model.addAttribute("msg",
					 * " <span class=\"font-red\">Already Uploaded Questions </span>"); }
					 */
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "uploadDescriptiveQuestions";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}
	/*
	 * @RequestMapping("/pushCandidate") public String pushCandidate() { //
	 * pushResultService.getConnection(); System.out.println();
	 * 
	 * return ""; }
	 */

	@RequestMapping("/answerKey")
	public String uploadAnswer(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "answerKey";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/uploadAnswerKey")
	public String uploadAnswerKey(Model model, @RequestParam("examId") int examId, @RequestParam("setNo") String setNo,
			@RequestParam("file") MultipartFile answerFile, @RequestParam("password") String password) {
		String status = null;
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession))
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} 
		else 
		{
			if (sessionHandler.authenticateAccess(httpSession)) 
			{
				try 
				{
					List<Question> questions = questionService.findBySetNoAndExamId(setNo, examId);
					status = answerOptionService.importCodexlsx(answerFile, questions, password);
					model.addAttribute("msg", status);
				} 
				catch (IOException e) 
				{
					model.addAttribute("msg", " <span class=\"font-red\">500 (internal Server Error) </span> " + e);
					e.printStackTrace();
				}
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "answerKey";
			} 
			else
			{
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/list")
	public String listQuestions(Model model)
	{
		List<ExamBean> examList = examService.findAllExams();
		model.addAttribute("values", examList);
		return "questionsList";
	}
	
	@RequestMapping("/list2")
	public String listQuestions2(Model model)
	{
		List<ExamBean> examList = examService.findAllExams();
		model.addAttribute("values", examList);
		return "questionsList2";
	}
	 
	@RequestMapping("/questionsList")
	public String questionsList(Model model, @RequestParam(name = "setNo", required = false) String setNo,
			@RequestParam(name = "examId", required = false) Integer examId) {
		List<ExamBean> list = new ArrayList<ExamBean>();
		List<Question> questions = null;
		Map<Integer, String> map = null;
		String page = "";
		ExamBean examBean = null;
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				if (setNo != null && examId != 0) {
					 examBean = examService.findById(examId);
					list.add(examBean);
					map = examService.getExanIdAndExamNameCodeMap(list);
					questions = questionService.findBySetNoAndExamId(setNo, examId);
				} else {
					map = examService.getExanIdAndExamNameCodeMap(examService.findAllExams());
					questions = questionService.findAll();
				}
				for (Question question : questions) {
					if (question.getHindiImageId() != null) {
						question.setBlob_hindi(questionService.findByImageId(question.getHindiImageId()));
					}
					if (question.getEnglishImageId() != null) {
						question.setBlob_english(questionService.findByImageId(question.getEnglishImageId()));
					}
					if (question.getTeluguImageId() != null) {
						question.setBlob_telugu(questionService.findByImageId(question.getTeluguImageId()));
					}
					if (question.getTamilImageId() != null) {
						question.setBlob_tamil(questionService.findByImageId(question.getTamilImageId()));
					}
					if (question.getKanadaImageId() != null) {
						question.setBlob_kanada(questionService.findByImageId(question.getKanadaImageId()));
					}
					if (question.getMarathiImageId() != null) {
						question.setBlob_marathi(questionService.findByImageId(question.getMarathiImageId()));
					}
					if (question.getUrduImageId() != null) {
						question.setBlob_urdu(questionService.findByImageId(question.getUrduImageId()));
					}
					for (Options option : question.getOptions()) {
						if (option.getHindiImageId() != null) {
							option.setBlob_hindi(questionService.findByImageId(option.getHindiImageId()));
						}
						if (option.getEnglishImageId() != null) {
							option.setBlob_english(questionService.findByImageId(option.getEnglishImageId()));
						}
						if (option.getTamilImageId() != null) {
							option.setBlob_tamil(questionService.findByImageId(option.getTamilImageId()));
						}
						if (option.getKanadaImageId() != null) {
							option.setBlob_kanada(questionService.findByImageId(option.getKanadaImageId()));
						}
						if (option.getMarathiImageId() != null) {
							option.setBlob_marathi(questionService.findByImageId(option.getMarathiImageId()));
						}
						if (option.getTeluguImageId() != null) {
							option.setBlob_telugu(questionService.findByImageId(option.getTeluguImageId()));
						}
						if (option.getUrduImageId() != null) {
							option.setBlob_urdu(questionService.findByImageId(option.getUrduImageId()));
						}
						
					}
				}
				model.addAttribute("examMap", map);
				model.addAttribute("questions", questions);
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
                model.addAttribute("examName", examBean.getExam_name());
				page = "test";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}
	
	
	@RequestMapping("/questionsList2")
	public String questionsList2(Model model, @RequestParam(name = "setNo", required = false) String setNo,
			@RequestParam(name = "examId", required = false) Integer examId) 
	{
		List<ExamBean> list = new ArrayList<ExamBean>();
		List<DescriptiveQuestion> questions = null;
		Map<Integer, String> map = null;
		String page = "";
		ExamBean examBean = null;
		if (sessionHandler.isSessionExpired(httpSession))
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} 
		else 
		{
			if (sessionHandler.authenticateAccess(httpSession))
			{
				if (setNo != null && examId != 0) 
				{
					 examBean = examService.findById(examId);
					list.add(examBean);
					map = examService.getExanIdAndExamNameCodeMap(list);
					questions = questionService.findBySetNoAndExamId2(setNo, examId);
				} 
				else
                {
					map = examService.getExanIdAndExamNameCodeMap(examService.findAllExams());
					questions = questionService.findAll2();
				}
				for (DescriptiveQuestion question : questions) 
				{
					if (question.getHindiImageId() != null) {
						question.setBlob_hindi(questionService.findByImageId2(question.getHindiImageId()));
					}
					if (question.getEnglishImageId() != null) {
						question.setBlob_english(questionService.findByImageId2(question.getEnglishImageId()));
					}
					
					
					
				}
				model.addAttribute("examMap", map);
				model.addAttribute("questions", questions);
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
                model.addAttribute("examName", examBean.getExam_name());
				page = "test2";
			} 
	
		else {
				page = "noaccess";
			}
		}
		return page;
	}


	@RequestMapping("/editQuestion")
	public String editQuestion(Model model, @RequestParam("questionId") int id) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("question", questionService.findQuestionById(id));
				model.addAttribute("values", examList);
				page = "editQuestion";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/redirectToUploadQuestions")
	public String redirectToUploadQuestion(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "uploadQuestions";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping(value = "/savingQuestionUrl", method = RequestMethod.POST)
	public String savingQuestion(@ModelAttribute Question question, Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				int examLocSessionId = 0;
				int totalQuestions = 0;
				String setNo = question.getSetNo();
				int count = questionService.findCountBySetNoAndExamId(setNo, question.getExamId());
				ExamBean exam = examService.findById(question.getExamId());
				totalQuestions = exam.getNoOfQuestions();
				if (count < totalQuestions) {
					List<ExamLocationSess> exams = examLocationService.findByExamId(question.getExamId());
					if (exams != null && !exams.isEmpty()) {
						try {
							examLocSessionId = exams.get(0).getId();
							question.setExamLocSessionId(examLocSessionId);
							List<Options> options = question.getOptions();
							question.setBankQuestionId(18);
							question.setQuestionType("Multiple Choice");
							for (Options option : options) {
								option.setQuestion(question);
							}
							question.setOptions(options);
							questionService.saveQuestion(question);
							model.addAttribute("msg", "<span class=\"font-green\">Question Added Sucessfully");
						} catch (Exception e) {
							model.addAttribute("msg",
									"<span class=\"font-red\">Exam Location Session Not Found </span>");
						}

					} 
					else 
					{
						model.addAttribute("msg", "<span class=\"font-red\">Exam Location Session Not Found </span>");	
					}

				} 
				else 
				{
					model.addAttribute("msg", "<span class=\"font-red\">Already Uploaded Questions </span>");
				}
				List<ExamBean> examList = examService.findAllExams();
				model.addAttribute("values", examList);
				page = "AddQuestion";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
	public String updateQuestion(@ModelAttribute Question question, Model model) {

		int examLocSessionId = 0;
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamLocationSess> exams = examLocationService.findByExamId(question.getExamId());
				if (exams != null && !exams.isEmpty()) {
					try {
						examLocSessionId = exams.get(0).getId();
						question.setExamLocSessionId(examLocSessionId);
						List<Options> options = question.getOptions();
						String str = question.getQuestionHindi();
						System.out.println("hindi question---> " + str);
						question.setBankQuestionId(18);
						question.setQuestionType("Multiple Choice");
						for (Options option : options) {
							option.setQuestion(question);
						}
						question.setOptions(options);
						questionService.saveQuestion(question);
						model.addAttribute("msg", "<span class=\"font-green\">Question Updated Sucessfully");
					} catch (Exception e) {
						model.addAttribute("msg", "Exam Location Session Not Found");
					}

				} else {
					model.addAttribute("msg", "Exam Location Session Not Found");
				}

				questionsList(model, null, 0);
				page = "questionsList";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/posturl")
	public String postdata(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("postCategory", postCategoryService.findAll());
				// model.addAttribute("posts", postService.getPostResult());
				page = "createPost";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public @ResponseBody String deletePost(@RequestParam int postId) {
		try {
			List<ExamBean> beans = examService.getDataBasedOnPostId(postId);

			for (ExamBean bean : beans) {
				// List<ExamLocationSess> listOfLocs =
				// examLocationService.findByExamId(bean.getId());
				/*
				 * for (ExamLocationSess examloc : listOfLocs) { List<Candidate> candidates =
				 * candidateService.findByExamSessionId(examloc.getId()); for (Candidate
				 * candidate : candidates) {
				 * queAnsService.deleteByCandidateId(candidate.getCandidate_id()); }
				 * candidateService.deleteByExamSessionId(examloc.getId());
				 * questionService.deleteQuestionByexamLocSessionId(examloc.getId()); }
				 */
				try {
					examLocationService.deleteByExamId(bean.getId());

				} catch (Exception e) {
					return "Could Not Delete Post At This Moment";
				}
			}
			examService.deleteByPostId(postId);
			int rowsAffected = postService.deletePostById(postId);
			if (rowsAffected != 0) {
				return "success";
			} else {
				return "Post Already deleted ";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@RequestMapping("/editPost")
	public String editPost(Model model, @RequestParam int id) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("postCategory", postCategoryService.findAll());
				model.addAttribute("post", postService.findByid(id));
				page = "updatePost";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/redirectpost")
	public String redirectPost(@ModelAttribute("postBean") Post postBean, Model model) {
		String msgBody = "";
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				if (postBean.getPostId() == 0) {
					msgBody = "added sucessfully";
				} else {
					msgBody = "updated sucessfully";
				}
				try {
					Post postSaved = postService.savePost(postBean);
					if (postSaved != null) {
						model.addAttribute("msg", "post \" " + postSaved.getPost_name() + " \"" + msgBody);

					} else {
						model.addAttribute("msg", "-1");
					}
				} catch (ConstraintViolationException | DataIntegrityViolationException e) {
					model.addAttribute("msg",
							"<p class=\"font-red\">post \" " + postBean.getPost_name() + " \"" + "Already Exist</p>");
				}

				model.addAttribute("postCategory", postCategoryService.findAll());
				page = "createPost";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/updatePost")
	public String updatePost(@ModelAttribute("postBean") Post postBean, Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				try {
					Post postSaved = postService.savePost(postBean);
					if (postSaved != null) {
						model.addAttribute("msg", "post \" " + postSaved.getPost_name() + " \"Updated Sucessfully");

					} else {
						model.addAttribute("msg", "-1");
					}
				} catch (ConstraintViolationException | DataIntegrityViolationException e) {
					model.addAttribute("msg",
							"<p class=\"font-red\">post \" " + postBean.getPost_name() + " \"" + "Already Exist</p>");
				}
				model.addAttribute("posts", postService.getPostResult());
				page = "postList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/listOfPosts")
	public String listOfPosts(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("posts", postService.getPostResult());
				page = "postList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/addPostCategory")
	public String addPostCategory(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				page = "addPostCategory";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/savePostCategory")
	public String savePostCategory(Model model, @ModelAttribute PostCategory postCategory) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				try {
					PostCategory postCategorySaved = postCategoryService.savePostCategory(postCategory);
					if (postCategorySaved != null) {
						model.addAttribute("msg", "Post category \" " + postCategorySaved.getPostCategoryName()
								+ " \" saved successfully");
					} else {
						model.addAttribute("msg", "-1");
					}
				} catch (DataIntegrityViolationException e) {
					model.addAttribute("msg", "<p style=\"color:red\">Post category \" "
							+ postCategory.getPostCategoryName() + " \" Already Exist </p>");
				}
				page = "addPostCategory";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/updatePostCategory")
	public String updatePostCategory(Model model, @ModelAttribute PostCategory postCategory) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				try {
					PostCategory postCategorySaved = postCategoryService.savePostCategory(postCategory);
					if (postCategorySaved != null) {
						model.addAttribute("msg", "Post category \" " + postCategorySaved.getPostCategoryName()
								+ " \" updated successfully");
					} else {
						model.addAttribute("msg", "-1");
					}
				} catch (DataIntegrityViolationException e) {
					model.addAttribute("msg", "<p style=\"color:red\">Post category \" "
							+ postCategory.getPostCategoryName() + " \" Already Exist </p>");
				}
				model.addAttribute("postCategory", postCategoryService.findAll());
				page = "postCategoryList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/postCategoryList")
	public String postCategoryList(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("postCategory", postCategoryService.findAll());
				page = "postCategoryList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/editPostCategory")
	public String editPostCategory(Model model, @RequestParam("postCategoryId") Integer id) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("postCategory", postCategoryService.findByPostCategoryId(id));
				return "updatePostCategory";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

}
