package com.ttipl.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ttipl.pojo.QuestionImages;
import com.ttipl.pojo.User;
import com.ttipl.service.BOANSendSMS;
import com.ttipl.service.LoginService;
import com.ttipl.service.QuestionService;
import com.ttipl.util.ControllerDTO;
import com.ttipl.util.UserDTO;
import com.ttipl.util.Utility;


@org.springframework.web.bind.annotation.RestController
public class RestController
{
	@Autowired
	private QuestionService service;

	@Autowired
	private LoginService loginService;
	@Autowired 
	HttpSession session;
	@Autowired
	BOANSendSMS boanSendSMS;
	@Autowired
	private Utility util;

	/**
	 * @param id
	 *            must not be null
	 * @return byte array.
	 */
	@RequestMapping(value = "/getQuestionImageById", method = { RequestMethod.GET }, produces = "image/jpg")
	public byte[] getQuestionImageById(@RequestParam("imageId") Integer id) {
		String base64 = "";
		if (id != null) {
			base64 = service.findByImageId(id);
			if ("".equalsIgnoreCase(base64) || base64 == null) {
				base64 = "Image Not Found";
			} else {
				return Base64.getDecoder().decode(base64);
			}
		} else {
			base64 = " must not be  null";
		}
		return base64.getBytes();
	}

	/**
	 * @param file
	 *            {@link MultipartFile}
	 * @return saved image (Id) {@link Integer}
	 */
	@RequestMapping(value = "/saveQuestionImage", method = RequestMethod.POST, consumes = "multipart/form-data", produces = "application/json")
	public ControllerDTO saveQuestionImage(@RequestParam("upload") MultipartFile file, HttpServletRequest request) {
		QuestionImages images = new QuestionImages();

		try {
			images.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			images.setImageId(0);
			images = service.saveImage(images);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ControllerDTO dto = new com.ttipl.util.ControllerDTO();
		dto.setUrl(getRequestPath(request) + "/getQuestionImageById?imageId=" + service.saveImage(images).getImageId());
		dto.setUploaded(1);
		dto.setFileName(file.getOriginalFilename());
		dto.setResponseText("Upload Success");
		return dto;

	}

	public String getRequestPath(HttpServletRequest req) {
		StringBuilder builder = new StringBuilder();
		builder.append(req.getScheme());
		builder.append("://");
		builder.append(req.getServerName());
		if (req.getServerPort() != 0) {
			builder.append(":" + req.getServerPort());
		}
		builder.append(req.getContextPath());
		// builder.append(req.getServletPath());
		return builder.toString();
	}

	@RequestMapping("finduser")
	public UserDTO findUser(@RequestParam("username") String username) {
		String otp=null;
		User user= loginService.findUser(username);
		UserDTO dto =new UserDTO();
		if(user !=null) {
			Random rd = new Random();
			otp = String.format("%04d", rd.nextInt(999999));
			while (otp.length() != 6) {
				otp = 0 + otp;
			}
			//System.out.println(otp);
			session.setAttribute("otp", otp);
			boanSendSMS.SendSMS1(user.getMobileNo(), otp+" is the OTP for password change for UserName "+user.getUsername());
			dto.setStatus(200);
			dto.setMessage("Success");
			dto.setValue(user.getUsername());
			dto.setUserId(user.getUserId());
			dto.setUsername(user.getUsername());
		}else {
			dto.setStatus(400);
			dto.setMessage("Not Found");
			dto.setValue(null);
		}
		return dto;
	}

	@RequestMapping("checkotp")
	public UserDTO checkotp(@RequestParam("otp") String otp) {
		String otp_session=(String)session.getAttribute("otp");
		UserDTO dto =new UserDTO();

		if(otp_session!=null) {
			if(otp_session.equalsIgnoreCase(otp)) {
				dto.setStatus(200);
				dto.setMessage("Success");
				dto.setValue("OK");
				//dto.setUserId(userId);
			}else {
				dto.setStatus(400);
				dto.setMessage("Entered Wrong otp");
				dto.setValue(null);
			}
		}
		return dto;
	}

	@RequestMapping("updatePassword")
	public UserDTO updatePassword(@RequestParam("password") String password,@RequestParam("userId") Integer userId) {
		User user=loginService.findUserByUserId(userId);
		try {
			user.setPassword(util.MD5Encrypt(password));
		} catch (NoSuchAlgorithmException e) {
			//e.printStackTrace();
		}
		UserDTO dto =new UserDTO();
		if(loginService.saveUser(user)!=null) {
			dto.setStatus(200);
			dto.setMessage("Success");
			dto.setValue("OK");
		}else {
			dto.setStatus(400);
			dto.setMessage("ERROR");
			dto.setValue(null);
		}
		return dto;
	}

}