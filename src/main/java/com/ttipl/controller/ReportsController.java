package com.ttipl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttipl.pojo.Candidate;
import com.ttipl.pojo.CandidateResults;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.pojo.Post;
import com.ttipl.pojo.ReportBean;
import com.ttipl.service.CandidateQueAnsService;
import com.ttipl.service.CandidateService;
import com.ttipl.service.ExamLocationService;
import com.ttipl.service.ExamService;
import com.ttipl.service.ExcelService;
import com.ttipl.service.PDFReport;
import com.ttipl.service.PostService;
import com.ttipl.service.ReportsManager;
import com.ttipl.service.ResultManager;
import com.ttipl.util.SessionHandling;

@Controller
public class ReportsController 
{

	@Autowired
	private PostService postService;

	@Autowired
	private ExcelService excelService;

	@Autowired
	private ExamService examService;
	@Autowired
	private ExamLocationService examLocationService;
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private CandidateQueAnsService queAnsService;
	@Autowired
	private ResultManager resultManager;
	@Autowired
	private ReportsManager reportsManager;
	@Autowired
	HttpSession httpSession;
	@Autowired
	private SessionHandling sessionHandler;
	@Autowired
	private PDFReport report;
	@Value("classpath:static/sys")
	Resource resourceSysFile;

	@Value("classpath:static/Reports")
	Resource resourceReportsFile;

	@RequestMapping(value = { "/reports", "/results" })
	public String redirectReportsIndex(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				return "reportsIndex";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/redirctStatisticReports")
	public String redirectStatisticsPage(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("exams", examService.findAllExams());
				page = "candidateStatistics";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/redirectResultProcessing")
	public String redirectresultProcessing(Model model) 
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
				model.addAttribute("exams", examService.findAllExams());
				page = "resultProcessing";
			} 
			else 
				page = "noaccess";
			
		}
		return page;
	}

	@RequestMapping("/resultProcessing")
	public String resultProcessing(Model model, @RequestParam String exam_loc_session_id, @RequestParam String examId) 
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
				queAnsService.deleteByResultByExam_isAndExamLocId(examId, exam_loc_session_id);
				List<ReportBean> result = resultManager.getDetailedResults(exam_loc_session_id, examId);
				int count = 0;

				for (ReportBean bean : result) 
				{
					CandidateResults cResult = new CandidateResults();
					DecimalFormat decim = new DecimalFormat("#.00");
					cResult.setCandidateId(bean.getHTnumber());
					cResult.setTotalCorrect(Integer.parseInt(bean.getTotalCorrect()));
					cResult.setTotalQuestions(Integer.parseInt(bean.getTotalQuestions()));
					cResult.setNormalizedMarks(bean.getRounded_marks());
					cResult.setTotalAttempted(Integer.parseInt(bean.getTotalAnswered()));
					cResult.setMarksObtained(Double.valueOf(decim.format(Double.parseDouble(bean.getTotalObtained()))));
					cResult.setResponses(bean.getResponses());
					cResult.setExamLocSessionId(bean.getExam_loc_id());
					cResult.setTotalNagetiveMarks(Double.valueOf(decim.format(Double.parseDouble(bean.getNegativeMarks()))));
					queAnsService.saveResult(cResult);
					count++;

				}
				if (count == 0) 
				{
					if (result.size() == 0) 
						model.addAttribute("msg", "<span class=\"font-red\">There is no records to process  !</span>");
					else 
						model.addAttribute("msg", "<span class=\"font-red\">Already processed  !</span>");
				} 
				else 
					model.addAttribute("msg","<span class=\"font-green\">Results( " + count + "  ) Processed succesfully</span>");
				
				model.addAttribute("exams", examService.findAllExams());
				page = "resultProcessing";
			} 
			else 
				page = "noaccess";
			
		}
		return page;
	}

	@RequestMapping("/redirctQuestionsAndAnswer")
	public String redirctQuestionsAndAnswer(Model model) 
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
				model.addAttribute("exams", examService.findAllExams());
				page = "candidateQueAndAnswer";
			}
			else 
				page = "noaccess";
		}
		return page;
	}

	@RequestMapping("/rediectCandidateResult")
	public String rediectCandidateResult(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				page = "candidateResultHallTicket";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/redirctCandidateResultsReport")
	public String redirctCandidateResultsReport(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("exams", examService.findAllExams());
				page = "candidateResultsReport";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping(value = "/findLocationAndSession", method = RequestMethod.POST)
	public @ResponseBody String findLocationAndSession(Model model, @RequestParam("examId") int id) {

		List<ExamLocationSess> examSessions = examLocationService.findByExamId(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String examsessions = "error";
		try {
			examsessions = objectMapper.writeValueAsString(examSessions);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return examsessions;
	}

	@RequestMapping(value = "/findSessions", method = RequestMethod.POST)
	public @ResponseBody String findSessions(Model model, @RequestParam("examId") String id) {
		List<ExamLocationSess> examSessions = examLocationService.findDistanctSessionsExamId(id);
		ObjectMapper objectMapper = new ObjectMapper();
		String examsessions = "error";
		try {
			examsessions = objectMapper.writeValueAsString(examSessions);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return examsessions;
	}

	@RequestMapping(value = "/isHallNoExist", method = RequestMethod.POST)
	public @ResponseBody String isHallNoExist(@RequestParam("candidateId") String id) {
		String status = "error";
		if (id != null) {
			Candidate candidate = candidateService.findByCandidateId(id);
			status = "false";
			if (candidate != null) {
				status = "true";
			}
		}
		return status;
	}

	@RequestMapping(value = "/findCandidatesBasedOnLocation", method = RequestMethod.POST)
	public @ResponseBody String findCandidatesBasedOnLocation(Model model, @RequestParam("locId") int id) {
		List<Object[]> examSessions = candidateService.findByExamSessionId1(id);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String examsessions = "error";
		try {
			examsessions = objectMapper.writeValueAsString(examSessions);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return examsessions;
	}

	@RequestMapping("/generateStatisticReports")
	public ResponseEntity<ByteArrayResource> generateStatisticsReports(@RequestParam int examId,
			@RequestParam int locId, @RequestParam String candidateId) throws Exception {
		ExamLocationSess examLocation = examLocationService.findById(locId);
		ExamBean exam = examService.findById(examId);
		Post post = postService.findByid(exam.getPostId());
		List<Object[]> objects = queAnsService.findStatisticsReport(candidateId);
		String path = report.getStasticsPDFReport(examLocation, exam, objects, post,
				resourceSysFile.getFile().getAbsolutePath(), resourceReportsFile.getFile().getAbsolutePath());
		return getPdfStream(path, "Cbt", "statisticsReport");
	}

	@RequestMapping("/generateQuestionsAndAnswer")
	public ResponseEntity<ByteArrayResource> generateQuestionsAndAnswer(@RequestParam int examId,
			@RequestParam int locId, @RequestParam String candidateId) throws Exception {
		ExamLocationSess examLocation = examLocationService.findById(locId);
		ExamBean exam = examService.findById(examId);
		Post post = postService.findByid(exam.getPostId());
		List<Object[]> objects = queAnsService.findcandidateQuestionsReport(candidateId);
		
		byte[] bytes = excelService.doExcelOfQuestion(objects);
		ByteArrayResource resource = new ByteArrayResource(bytes);
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=EXCEL_DATA.xlsx");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		/*
		 * return ResponseEntity.ok().headers(header).contentLength(bytes.length)
		 * .contentType(MediaType.parseMediaType("application/octet-stream")).body(
		 * resource);
		 */
		
		String path = report.getCandidateQueAnsweReport(examLocation, exam, objects, post,
				resourceSysFile.getFile().getAbsolutePath(), resourceReportsFile.getFile().getAbsolutePath());
		return getPdfStream(path, "Cbt", "QuestionsAndAnswer");
		 
		
	}

	@RequestMapping("/allque")
	public void generateQuestionsAndAnswer() throws Exception {
		ExamLocationSess examLocation = examLocationService.findById(45);
		ExamBean exam = examService.findById(33);
		Post post = postService.findByid(exam.getPostId());
		List<Candidate> candidates=candidateService.findByExamSessionId(45);
		for(Candidate cd:candidates) 
		{
		List<Object[]> objects = queAnsService.findcandidateQuestionsReport(cd.getCandidate_id());
		String path = report.getCandidateQueAnsweReport(examLocation, exam, objects, post,
				resourceSysFile.getFile().getAbsolutePath(), "D:\\RRC Chennai\\indivisual_reports\\");
		System.out.println(path);
		}
		
	}

	@RequestMapping("/generateCandidateResultsReport")
	public ResponseEntity<ByteArrayResource> generateCandidateResultsReport(@RequestParam int examId,
			@RequestParam int locId, @RequestParam String candidateId) throws Exception {
		ExamLocationSess examLocation = examLocationService.findById(locId);
		ExamBean exam = examService.findById(examId);
		Post post = postService.findByid(exam.getPostId());

		List<Object[]> objects = null;
		if (candidateId.equalsIgnoreCase("all")) {
			objects = queAnsService.findInCandidateResultTblByCandidateId(locId);
		} else {
			objects = queAnsService.findInCandidateResultTblByCandidateId(candidateId, locId);
		}
		/*
		 * List<Object[]> objects = null; if (candidateId.equalsIgnoreCase("all")) {
		 * objects = queAnsService.findCandidateReports(); } else { objects =
		 * queAnsService.findCandidateReportsById(candidateId); }
		 */
		List<ReportBean> reportbean = reportsManager.doObjectMarksTObean(objects);
		String path = report.getCandidateResultReport(examLocation, exam, reportbean, post,
				resourceSysFile.getFile().getAbsolutePath(), resourceReportsFile.getFile().getAbsolutePath());
		return getPdfStream(path, "Cbt", "QuestionsAndAnswer");
	}

	@RequestMapping("/candidateResultsReportList")
	public String CandidateResultsReportList(@RequestParam int examId, @RequestParam int locId,
			@RequestParam String candidateId, Model model) throws Exception {
		// ExamLocationSess examLocation = examLocationService.findById(locId);
		ExamBean exam = examService.findById(examId);
		Post post = postService.findByid(exam.getPostId());

		List<Object[]> objects = null;
		if (candidateId.equalsIgnoreCase("all")) {
			objects = queAnsService.findInCandidateResultTblByCandidateId(locId);
		} else {
			objects = queAnsService.findInCandidateResultTblByCandidateId(candidateId, locId);
		}
		/*
		 * List<Object[]> objects = null; if (candidateId.equalsIgnoreCase("all")) {
		 * objects = queAnsService.findCandidateReports(); } else { objects =
		 * queAnsService.findCandidateReportsById(candidateId); }
		 */
		List<ReportBean> reportbean = reportsManager.doObjectMarksTObean(objects);
		model.addAttribute("reportBean", reportbean);
		model.addAttribute("exams", examService.findAllExams());
		model.addAttribute("examId", examId);
		model.addAttribute("locId", locId);
		model.addAttribute("candidateId", candidateId);
		return "candidateResultsReport";
	}

	@RequestMapping("/generateCandidateResult")
	public ResponseEntity<ByteArrayResource> generateCandidateResult(@RequestParam String candidateId)
			throws Exception {
		Candidate candidate = candidateService.findByCandidateId(candidateId);
		ExamLocationSess examLocation = examLocationService.findById(candidate.getExam_loc_session_id());
		ExamBean exam = examService.findById(examLocation.getExamId());
		Post post = postService.findByid(exam.getPostId());
		List<Object[]> objects = null;
		objects = queAnsService.findInCandidateResultTblByCandidateId(candidateId, examLocation.getId());
		// List<ReportBean> reportbean =
		// reportsManager.assignObjectArrayToCandidateResultBeanarray(objects);
		List<ReportBean> reportbean = reportsManager.doObjectMarksTObean(objects);
		String path = report.getCandidateResultReport(examLocation, exam, reportbean, post,
				resourceSysFile.getFile().getAbsolutePath(), resourceReportsFile.getFile().getAbsolutePath());
		return getPdfStream(path, "Cbt", "QuestionsAndAnswer");
	}

	public ResponseEntity<ByteArrayResource> getPdfStream(String filePath, String dateFromTo, String fileName) {
		InputStream stream = null;
		byte[] bytes = null;
		try {

			File file = new File(filePath);
			stream = new FileInputStream(file);

			bytes = new byte[stream.available()];
			stream.read(bytes);
			stream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ByteArrayResource resource = new ByteArrayResource(bytes);
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.ACCEPT_ENCODING, "utf-8");
		header.add(HttpHeaders.ACCEPT_CHARSET , "utf-8");
		header.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + dateFromTo + "_" + fileName + ".pdf");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		return ResponseEntity.ok().headers(header).contentLength(bytes.length)
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

	@RequestMapping(value = "/downloadExcelReport")
	public ResponseEntity<ByteArrayResource> excelDowload(@RequestParam int examId, @RequestParam int locId,
			@RequestParam String candidateId) throws Exception {
		// ExamLocationSess examLocation = examLocationService.findById(locId);
		ExamBean exam = examService.findById(examId);
		Post post = postService.findByid(exam.getPostId());
		List<Object[]> objects = null;
		if (candidateId.equalsIgnoreCase("all")) {
			objects = queAnsService.findInCandidateResultTblByCandidateId(locId);
		} else {
			objects = queAnsService.findInCandidateResultTblByCandidateId(candidateId, locId);
		}
		/*
		 * List<Object[]> objects = null; if (candidateId.equalsIgnoreCase("all")) {
		 * objects = queAnsService.findCandidateReports(); } else { objects =
		 * queAnsService.findCandidateReportsById(candidateId); }
		 */
		List<ReportBean> reportbean = reportsManager.doObjectMarksTObean(objects);
		byte[] bytes = excelService.doExcelFileReport(reportbean, post);
		ByteArrayResource resource = new ByteArrayResource(bytes);
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=EXCEL_DATA.xlsx");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		return ResponseEntity.ok().headers(header).contentLength(bytes.length)
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

	@RequestMapping(value = "/downloadExcelDetailedReport")
	public ResponseEntity<ByteArrayResource> excelDowloadDetailed(@RequestParam int examId, @RequestParam int locId,
			@RequestParam String candidateId) throws Exception 
	{
		// ExamLocationSess examLocation = examLocationService.findById(locId);
		ExamBean exam = examService.findById(examId);
		// Post post = postService.findByid(exam.getPostId());
		List<Object[]> objects = null;
		if (candidateId.equalsIgnoreCase("all")) {
			objects = queAnsService.findInCandidateResultTblByCandidateId(locId);
		} else {
			objects = queAnsService.findInCandidateResultTblByCandidateId(candidateId, locId);
		}
		//List<Object[]> objectsQue = queAnsService.findcandidateQuestionsReport(candidateId);

		List<ReportBean> reportbean = reportsManager.doObjectMarksTObean(objects);
		byte[] bytes = excelService.doExcelFileDetailedReport(reportbean, exam);
		ByteArrayResource resource = new ByteArrayResource(bytes);
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=EXCEL_DATA.xlsx");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		return ResponseEntity.ok().headers(header).contentLength(bytes.length)
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}

	@RequestMapping("/stats")
	public String stats(Model model, @RequestParam(name = "locId", required = false) String locId,
			@RequestParam(name = "examId", required = false) String examId) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				String startTime = "";
				String endTime = "";
				String[] session = null;
				int total_candidates = 0;
				int total_can_exam_starts = 0;
				if (locId != null && examId != null) {
					if (locId.equalsIgnoreCase("all")) {
						startTime = "%";
						endTime = "%";
					} else {
						session = locId.split("-");
						startTime = session[0];
						endTime = session[1];

					}
					if (examId.equalsIgnoreCase("all")) {
						examId = "%";
					} else {

					}

					total_candidates = candidateService.findDistinctByCandidateId(startTime, endTime, examId);
					total_can_exam_starts = queAnsService.totalCandidatesExamStarted(startTime, endTime, examId);
					model.addAttribute("total_candidates", total_candidates);
					model.addAttribute("total_can_exam_starts", total_can_exam_starts);
					if (total_candidates == 0) {
						total_candidates = 1;
					}
					model.addAttribute("total_Percentage", ((total_can_exam_starts * 100) / total_candidates) + "%");

					model.addAttribute("sessions", queAnsService.presentTotalCount(startTime, endTime, examId));
				}
				model.addAttribute("locId", locId);
				model.addAttribute("examId", examId);
				model.addAttribute("exams", examService.findAllExams());
				page = "dashboard";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/CorrectAnswer")
	public String answer(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<Object[]> list = queAnsService.getListData();
				model.addAttribute("list", list);
				page = "CorrectAnswer";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}
}
