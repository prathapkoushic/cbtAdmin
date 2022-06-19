package com.ttipl.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.service.ExamService;
import com.ttipl.service.PostService;
import com.ttipl.util.SessionHandling;
import com.ttipl.util.Utility;
import com.ttipl.service.BOANSendSMS;
import com.ttipl.service.ExamLocationService;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.ExamLocationSess;
import com.ttipl.pojo.LoginBean;
import com.ttipl.pojo.OtpVerification;
import com.ttipl.pojo.Post;
import com.ttipl.pojo.Role;
import com.ttipl.pojo.User;
import com.ttipl.service.LoginService;

@org.springframework.stereotype.Controller
public class Contoller
{
	@Autowired
	private LoginService loginService;
	@Autowired
	private ExamService examService;
	@Autowired
	private PostService postService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	private SessionHandling sessionHandler;
	@Autowired
	private ExamLocationService examLocationService;
	@Autowired
	BOANSendSMS boanSendSMS;
	@Autowired
	private Utility util;

	@RequestMapping({"/","index"})
	public String redirectFirstPage() {
		return "index";
	}

	@RequestMapping("/logout")
	public String logouturl(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
		} else {
			httpSession.removeAttribute("role");
			page = "index";
		}
		return page;
	}

	@RequestMapping("/loginurl")
	public String login(@ModelAttribute("loginBean") LoginBean loginBean, Model model) {
		String page = "addPostCategory";
		List<LoginBean> record = loginService.getdata(loginBean.getUsername(), loginBean.getPassword());
		User user = null;
		try {
			if(loginBean.getUsername()==null&&loginBean.getPassword()==null) {
				return "index";
			}
			user = loginService.userLogin(loginBean.getUsername(), util.MD5Encrypt(loginBean.getPassword()));
		} catch (NoSuchAlgorithmException e) 
		{}
		if (!record.isEmpty()) {
			httpSession.setAttribute("role", "admin");
			httpSession.setMaxInactiveInterval(300);
			return page;
		} else if (user != null) {
			httpSession.setAttribute("role", user);
			httpSession.setMaxInactiveInterval(300);
			Role role = loginService.findAllRoleId(user.getRoleId());
			page = sessionHandler.userLandingPageResolver(role);
		} else {
			page = "index";
			model.addAttribute("msg", "Invalid Credentials");
		}
		return page;

	}

	
	@RequestMapping("/createexamurl")
	public String redirect2(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("value", postService.getPostResult());
				page = "createExam";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/user")
	public String user(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("roles", loginService.findAllRoles());
				page = "user";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/forgot")
	public String forgot(Model model) {
		return  "forgot";
	}

	@RequestMapping("/addUser")
	public String addUser(Model model, @ModelAttribute User user) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				if (user != null) {
					User userExist = loginService.findUser(user.getUsername());
					if (userExist == null) {
						try {
							user.setPassword(util.MD5Encrypt(user.getPassword()));
							loginService.saveUser(user);
							model.addAttribute("msg", "added success fully");
						} catch (NoSuchAlgorithmException e) {
						}
					} else {
						model.addAttribute("msg", "username already exist");
					}
					page = "user";
				}
			} else {
				page = "noaccess";
			}
			model.addAttribute("roles", loginService.findAllRoles());
		}

		return page;
	}

	@RequestMapping(value = "/examformurl", method = RequestMethod.POST)
	public String post(Model model, @ModelAttribute("examBean") ExamBean examBean) 
	{
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) 
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) 
			{
				String message = examService.getData(examBean);
				model.addAttribute("value", postService.getPostResult());

				model.addAttribute("msg", message);
				page = "createExam";
			} 
			else 
			{
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/examlist")
	public String redirectList(Model model, @RequestParam(name = "postId", required = false) Integer postId)
	{
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				if (postId == null) {

					Post post = postService.getPostResultRandomOne();
					if (post != null) {
						postId = post.getPostId();
					}
					model.addAttribute("values", examService.findAllExamBeans());
				} else if (postId == -1) {
					model.addAttribute("values", examService.findAllExamBeans());

				} else {
					model.addAttribute("values", examService.findAllExamBeansByPostId(postId));
				}
				model.addAttribute("value", postService.getPostResult());
				page = "examList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/getResultsBasedonPostId")
	public String getResultsBasedonPostId(Model model, @RequestParam("postId") int postId) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {

				model.addAttribute("value", postService.getPostResult());
				model.addAttribute("values", examService.getDataBasedOnPostId(postId));
				page = "examList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/editurl{id}")
	public String redirect3(Model model, @PathVariable("id") int givenID)
	{
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("updateValues", examService.getDataForUpdate(givenID));
				model.addAttribute("value", postService.getPostResult());
				page = "updateExam";
			}
			else 
			{
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/examupdateurl")
	public String redirect5(Model model, @RequestParam("id") int givenID,
			@ModelAttribute("examBean") ExamBean examBean) 
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
				examBean.setId(givenID);
				examService.getData(examBean);
				
				model.addAttribute("values", examService.findAllExamBeans());
				model.addAttribute("value", postService.getPostResult());
				page = "examList";
			} 
			else 
			{
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/redirectLocationSessionListPage")
	public String redirectLocationSessionListPage(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {

				page = "sessionList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/createlocation")
	public String redirect2(Model model , @ModelAttribute("examLocationstSession") ExamLocationService examLocationSession) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) 
		{
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {

			if (sessionHandler.authenticateAccess(httpSession)) 
			{
				model.addAttribute("values", examService.getResult());
				page = "examLocationWithSession";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/createLocationSession")
	public String insertLocation(Model model, @ModelAttribute("examLocationSess") ExamLocationSess examLocationSess)
			throws ParseException {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				examLocationService.saveData(examLocationSess);
				model.addAttribute("values", examService.getResult());
				model.addAttribute("msg", "Succesfully Added Exam Location Session.");
				page = "examLocationWithSession";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/uploadExamLocation")
	public String redirectToExamLocationSession(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("values", examService.getResult());
				page = "uploadLocationSession";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping(value = ("/uploadLocationSessionFile"), headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public String redirectToUploadLocationSessionFile(@RequestParam("file") MultipartFile file,
			@RequestParam("exam_id") int exam_id, Model model, @RequestParam("password") String password) {
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
						msg = examService.importCodexlsx(file, exam_id, password);
					}

				} catch (IOException e) {

					e.printStackTrace();
				}
				model.addAttribute("msg", msg);
				model.addAttribute("values", examService.getResult());
				page = "uploadLocationSession";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/locationSessionList")
	public String sessionList(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<Object[]> list = examLocationService.findSessionWithExamName();
				model.addAttribute("values", list);
				page = "sessionList";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	

	@RequestMapping("updateLocation{id}")
	public String updateLocation(Model model, @PathVariable("id") int givenID,
			@ModelAttribute("exsession") ExamLocationSess exsession) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				exsession.setId(givenID);
				examLocationService.saveData(exsession);
				List<Object[]> list = examLocationService.findSessionWithExamName();
				model.addAttribute("values", list);
				page = "sessionList";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/editExamLocSession")
	public String editSess(Model model, @RequestParam("id") int givenID) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				ExamLocationSess el = examLocationService.getDataForUpdate(givenID);
				List<ExamBean> li = examService.getResult();

				model.addAttribute("updateValues", el);
				model.addAttribute("values", li);
				page = "updateSession";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/deleteExamLocSession")
	public String delete(Model model, @RequestParam("id") int givenID) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamLocationSess> list = examLocationService.getData();
				model.addAttribute("value", list);
				try {
					examLocationService.delete(givenID);
				} catch (Exception e) {
					return "Could Not Delete Exam Location At This Moment ";
				}
				page = "updateSession";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	/*
	 * @RequestMapping("/redirectToPushDataPage") public String
	 * redirectToPushDataPage(Model model) { model.addAttribute("examNames",
	 * examService.getResult()); model.addAttribute("LocationSession",
	 * examService.findSessions()); return "PushQuestionsBasedOnSelection"; }
	 */

	/*
	 * @RequestMapping("/pushQuestionsUrl") public String
	 * pushQuestionsUrl(@RequestParam("examId") int examId,
	 * 
	 * @RequestParam("LocationSessionId") int locationSessionId, Model model) throws
	 * SQLException { int postId = examService.getPostIdBasedOnExamId(examId);
	 * String ipaddress =
	 * examService.getIdAddressBasedOnLocationSessionId(locationSessionId); try {
	 * DatapushDao dataPushDao = new DatapushDao(); String localDBURL =
	 * env.getProperty("spring.datasource.url"); DriverManagerDataSource
	 * dataSourceLocal = new DriverManagerDataSource();
	 * dataSourceLocal.setDriverClassName(env.getProperty(
	 * "spring.datasource.driver-class-name")); dataSourceLocal.setUrl(localDBURL);
	 * dataSourceLocal.setUsername(env.getProperty("spring.datasource.username"));
	 * dataSourceLocal.setPassword(env.getProperty("spring.datasource.password"));
	 * String serverCred = env.getProperty("spring.second-datasource.url"); String
	 * serverDBURL = serverCred.replaceAll("localhost", ipaddress); // String
	 * serverDBURL = localDBURL.replaceAll("localhost", ipaddress);
	 * DriverManagerDataSource dataSourceServer = new DriverManagerDataSource(); //
	 * dataSourceServer.setDriverClassName(env.getProperty(
	 * "spring.second-datasource.driver-class-name"));
	 * dataSourceServer.setUrl(serverDBURL);
	 * dataSourceServer.setUsername(env.getProperty(
	 * "spring.second-datasource.username"));
	 * dataSourceServer.setPassword(env.getProperty(
	 * "spring.second-datasource.password"));
	 * 
	 * try { dataPushDao.pushPostsToLocationServerDb(dataSourceLocal,
	 * dataSourceServer, postId); String status1 =
	 * dataPushDao.pushExamsToLocationServerDb(dataSourceLocal, dataSourceServer,
	 * examId); String status2 =
	 * dataPushDao.pushExamLocationSessionsToLocationServerDb(dataSourceLocal,
	 * dataSourceServer, examId); String status3 =
	 * dataPushDao.pushCandidatesToLocationServerDb(dataSourceLocal,
	 * dataSourceServer, examId, locationSessionId); String status4 =
	 * dataPushDao.pushQuestionsToLocationServerDb(dataSourceLocal,
	 * dataSourceServer, examId, locationSessionId);
	 * 
	 * int[] QuestiionId =
	 * examService.findQuestionIdBasedOnExamIdAndLocationSessionId(examId); for
	 * (Integer questionId : QuestiionId) { String status5 =
	 * dataPushDao.pushOptionsBasedOnQuestionIdToLocationServer(dataSourceLocal,
	 * dataSourceServer, questionId); } model.addAttribute("status",
	 * "Data Pushed Succesfully."); } catch (Exception e) {
	 * model.addAttribute("status", "Database credentials doesn't match."); }
	 * 
	 * return "PushQuestionsBasedOnSelection"; } catch (Exception e) {
	 * model.addAttribute("status", "Data didn't pushed to Local DB"); return
	 * "PushQuestionsBasedOnSelection"; }
	 * 
	 * }
	 */

	@RequestMapping("/assignSetNo")
	public String assignSetNoUrl(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("examNames", examService.getResult());
				model.addAttribute("LocationSession", examService.findSessions());
				page = "assignSetNo";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/assigningSetNO")
	public String assigningSetNO(Model model, @RequestParam("examId") int examId,
			@RequestParam("LocationSessionId") int locationSessionId, @RequestParam("setNo") String setNo) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				int data = examService.updateExamLocationSession(locationSessionId, examId, setNo);
				if (data != 0) {
					model.addAttribute("data", "Succesfully Assigned");
				} else {
					model.addAttribute("data", "Didn't Updated");
				}
				model.addAttribute("examNames", examService.getResult());
				model.addAttribute("LocationSession", examService.findSessions());
				page = "assignSetNo";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/sendOtps")
	public String redirectToSendOTPs(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("values", examService.getResult());
				page = "sendOTPs";
			} else {
				page = "noaccess";
			}
		}
		return page;
	}

	@RequestMapping("/redirectGenerateOTP")
	public String redirectGenerateOTP(Model model) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				model.addAttribute("values", examService.getResult());
				page = "generateOTP";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/OTPlist")
	public String OTPlist(Model model, @RequestParam(name = "examId", required = false) String examId) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				if (examId == null) {
					examId = "%";
				}
				model.addAttribute("sessions", examService.findOTPByExamId(examId));
				model.addAttribute("values", examService.getResult());
				model.addAttribute("examId", examId);

				page = "OTPlist";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/OtptoLocation")
	public String OtptoLocationUrl(Model model, @RequestParam("examId") int examId,
			@RequestParam("sessionId") int sessionId) {
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else {
			if (sessionHandler.authenticateAccess(httpSession)) {
				List<ExamLocationSess> sessions = new ArrayList<ExamLocationSess>();

				if (sessionId == -1) {
					sessions = examLocationService.findByExamId(examId);

				} else {
					sessions.add(examLocationService.findById(sessionId));
				}
				String otp = null;
				int counter = 0;
				int not_sentcounter = 0;
				for (ExamLocationSess examLocationSess : sessions) {
					otp = examService.findIsOTPIsOrNot(examLocationSess.getId());
					if (otp == null) {
						not_sentcounter++;
					} else {
						String string = boanSendSMS.SendSMS1(examLocationSess.getMobileNumber(),
								otp + ": is your  OTP ");
						counter++;
						System.out.println(string);
					}
				}
				if (counter != 0 && not_sentcounter == 0) {
					model.addAttribute("msg",
							"<span class=\"font-green\">Succesfully   sent OTP's to " + counter + " Session(s)</span>");
				} else if (counter != 0 && not_sentcounter != 0) {
					model.addAttribute("msg", "<span class=\"font-green\">Succesfully   sent OTP's to " + counter
							+ " Session(s).  But We found " + not_sentcounter + " records need to generate OTP</span>");
				} else {
					model.addAttribute("msg",
							"<span class=\"font-red\">Please generate OTP's. Records not found</span>");
				}
				model.addAttribute("values", examService.getResult());
				page = "sendOTPs";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/generateOTP")
	public String generateOTP(Model model, @RequestParam("examId") int examId,
			@RequestParam("sessionId") int sessionId) {
		List<ExamLocationSess> sessions = new ArrayList<ExamLocationSess>();
		String page = "";
		if (sessionHandler.isSessionExpired(httpSession)) {
			page = "index";
			model.addAttribute("msg", "session expired");
		} else 
		{
			if (sessionHandler.authenticateAccess(httpSession))
			{
				if (sessionId == -1) {
					sessions = examLocationService.findByExamId(examId);

				} else {
					sessions.add(examLocationService.findById(sessionId));
				}
				String otp = null;
				int counter = 0;
				for (ExamLocationSess examLocationSess : sessions) {
					OtpVerification otpVerification = new OtpVerification();
					otp = examService.findIsOTPIsOrNot(examLocationSess.getId());
					if (otp == null) {
						Random rd = new Random();
						otp = String.format("%04d", rd.nextInt(999999));
						while (otp.length() != 6) {
							otp = 0 + otp;
						}
						otpVerification.setDateCreated(LocalDateTime.now().toString());
						otpVerification.setExam_loc_session_id(examLocationSess.getId());
						otpVerification.setExamId(examId);
						otpVerification.setOtp(otp);
						examService.save(otpVerification);
						counter++;
					}
					
				}
				model.addAttribute("msg", "Succesfully   generated OTP's to " + counter + " Session(s)");
				model.addAttribute("values", examService.getResult());
				page = "sendOTPs";
			} else {
				page = "noaccess";
			}
		}
		return page;

	}

	@RequestMapping("/session_user_list")
	public String session_user_list(@RequestParam(name = "examId", required = false) Integer examId, Model model) 
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
				if (examId == null) {
					model.addAttribute("sessions", examLocationService.findAllSessionsWithExam());
				} else {
					model.addAttribute("sessions", examLocationService.findAllSessionsWithExam(examId));
				}
				model.addAttribute("values", examService.getResult());

				page = "session_username_list";
			} 
			else 
			{
				page = "noaccess";
			}
		}
		return page;
	}

}