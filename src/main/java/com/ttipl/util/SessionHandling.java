package com.ttipl.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ttipl.pojo.Role;
import com.ttipl.pojo.User;
import com.ttipl.service.LoginService;

/**
 * @author gopi prasad garimidi
 * @Date 18-12-2020
 * 
 */
@Component
public class SessionHandling {
	@Autowired
	private LoginService loginService;
	@Autowired
	HttpServletRequest httpServletRequest;

	/**
	 * @param httpSession
	 * @return {@link Boolean} it checks session is alive or not if exists it return
	 *         {@link false} else return {@link true}
	 */
	public boolean isSessionExpired(HttpSession httpSession) {
		if (httpSession != null) {
			if (httpSession.getAttribute("role") != null) {
				System.out.println(httpSession.getAttribute("role"));
				return false;
			}
		}
		return true;
	}

	public String userLandingPageResolver(Role role) {
		String status = "noaccess";
		if (role != null) {
			String role_access = role.getRole();
			switch (role_access) {
			case RoleMapping.POST:
				status = RoleMapping.POST_PAGE;
				break;
			case RoleMapping.POST_CATEGORY:
				status = RoleMapping.POST_CATEGORY_PAGE;
				break;
			case RoleMapping.EXAM:
				status = RoleMapping.EXAM_PAGE;
				break;
			case RoleMapping.EXAM_LOC_SESSION:
				status = RoleMapping.EXAM_LOC_SESSION_PAGE;
				break;
			case RoleMapping.QUESTION:
				status = RoleMapping.QUESTION_PAGE;
				break;
			case RoleMapping.CANIDATE:
				status = RoleMapping.CANIDATE_PAGE;
				break;
			case RoleMapping.ANSWER_KEY:
				status = RoleMapping.ANSWER_KEY_PAGE;
				break;
			case RoleMapping.REPORTS:
				status = RoleMapping.REPORTS_PAGE;
				break;
			case RoleMapping.PROCESS_RESULT:
				status = RoleMapping.PROCESS_RESULT_PAGE;
				break;
			case RoleMapping.OTP:
				status = RoleMapping.OTP_PAGE;
				break;

			default:

				break;
			}

		}
		return status;
	}

	public boolean authenticateAccess(HttpSession session) {
		boolean status = false;
		//UriComponents obj = ServletUriComponentsBuilder.fromCurrentRequest().build();
		 //url = obj.getPath();
		String url = httpServletRequest.getServletPath();
		if (url.contains("/")) {
			url = url.replace("/", "");
		}
		Object object = (Object) session.getAttribute("role");
		if (object instanceof String) {
			status = true;
		} else {
			User user = (User) object;
			Role role = loginService.findAllRoleId(user.getRoleId());
			String role_access = role.getRole();
			switch (role_access) {
			case RoleMapping.POST:
				status = checkValue(RoleMapping.POST_URLS, url);
				break;
			case RoleMapping.POST_CATEGORY:
				status = checkValue(RoleMapping.POST_CATEGORY_URLS, url);
				break;
			case RoleMapping.EXAM:
				status = checkValue(RoleMapping.EXAM_URLS, url);
				break;
			case RoleMapping.EXAM_LOC_SESSION:
				status = checkValue(RoleMapping.EXAM_LOC_SESSION_URLS, url);
				break;
			case RoleMapping.QUESTION:
				status = checkValue(RoleMapping.QUESTION_URLS, url);
				break;
			case RoleMapping.CANIDATE:
				status = checkValue(RoleMapping.QUESTION_URLS, url);
				break;
			case RoleMapping.ANSWER_KEY:
				status = checkValue(RoleMapping.ANSWER_KEY_URLS, url);
				break;
			case RoleMapping.REPORTS:
				status = checkValue(RoleMapping.REPORTS_URLS, url);
				break;
			case RoleMapping.PROCESS_RESULT:
				status = checkValue(RoleMapping.PROCESS_RESULT_URLS, url);
				break;
			case RoleMapping.OTP:
				status = checkValue(RoleMapping.OTP_URLS, url);
				break;

			default:

				break;
			}
		}

		return status;
	}

	public boolean checkValue(List<String> list, String value) {
		boolean status = false;
		for (String builtinValue : list) {
			if (builtinValue.equalsIgnoreCase(value)) {
				status = true;
				break;
			}
		}
		return status;
	}
}
