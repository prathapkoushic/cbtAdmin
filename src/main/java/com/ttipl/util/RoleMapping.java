package com.ttipl.util;

import java.util.Arrays;
import java.util.List;

public class RoleMapping {
	// URL MAPPING
	public static final List<String> POST_CATEGORY_URLS = Arrays.asList("addPostCategory", "savePostCategory", "postCategoryList","editPostCategory","updatePostCategory");
	public static final List<String> POST_URLS = Arrays.asList("posturl", "listOfPosts", "editPost","redirectpost","updatePost");
	public static final List<String> EXAM_URLS = Arrays.asList("createexamurl", "examlist", "examupdateurl","editurl","getResultsBasedonPostId","examformurl");
	public static final List<String> EXAM_LOC_SESSION_URLS = Arrays.asList("createlocation", "locationSessionList", "editExamLocSession","uploadExamLocation","uploadLocationSessionFile","uploadExamLocation","createLocationSession","redirectLocationSessionListPage");
	public static final List<String> CANIDATE_URLS = Arrays.asList("createcandidate", "CandidateList", "uploadurl","candidatesByExam","editCandidate","candEdit","saveCandidate","updateCandidate","uploadcandidatedata","candDelete");
	public static final List<String> QUESTION_URLS = Arrays.asList("createQuestion","editQuestion", "questionsList", "redirectToUploadQuestions","updateQuestion","savingQuestionUrl","uplaodQuestions");
	public static final List<String> OTP_URLS = Arrays.asList("redirectGenerateOTP", "sendOtps", "OTPlist","OtptoLocation","generateOTP");
	public static final List<String> PROCESS_RESULT_URLS = Arrays.asList("redirectResultProcessing","resultProcessing");
	public static final List<String> ANSWER_KEY_URLS = Arrays.asList("answerKey","uploadAnswerKey");
	public static final List<String> REPORTS_URLS = Arrays.asList("redirctQuestionsAndAnswer", "redirctCandidateResultsReport", "rediectCandidateResult","redirctStatisticReports");
// DB COMPATILBLES ROLES
	public static final String POST_CATEGORY="POST_CATEGORY_ACCESS";
	public static final String POST="POST_ACCESS";
	public static final String EXAM="EXAM_ACCESS";
	public static final String EXAM_LOC_SESSION="EXAM_LOC_SESSION_ACCESS";
	public static final String CANIDATE="CANIDATE_ACCESS";
	public static final String QUESTION="QUESTION_ACCESS";
	public static final String OTP="OTP_ACCESS";   
	public static final String PROCESS_RESULT="PROCESS_RESULT_ACCESS";
	public static final String ANSWER_KEY="ANSWER_KEY_ACCESS";
	public static final String REPORTS="REPORTS_ACCESS"; 
	
	// LANDING PAGES
	public static final String POST_CATEGORY_PAGE="addPostCategory";
	public static final String POST_PAGE="createPost";
	public static final String EXAM_PAGE="createExam";
	public static final String EXAM_LOC_SESSION_PAGE="examLocationWithSession";
	public static final String CANIDATE_PAGE="candidate";
	public static final String QUESTION_PAGE="AddQuestion";
	public static final String OTP_PAGE="generateOTP";   
	public static final String PROCESS_RESULT_PAGE="resultProcessing";
	public static final String ANSWER_KEY_PAGE="answerKey";
	public static final String REPORTS_PAGE="candidateResultsReport"; 	




}
