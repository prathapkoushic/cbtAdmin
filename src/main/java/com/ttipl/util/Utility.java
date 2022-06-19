package com.ttipl.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/**
 * @Author Gopi prasad
 * @TIMIMG TECHNOLOIES INDIA PVT LTD
 * 
 */
@Component
public class Utility {

	/**
	 * @Date : 2020-12-08
	 * @Des : Check ip address
	 * @return: boolean
	 */
	public boolean isIPAddressCorrect(String IPAddress) {
		boolean status = false;
		String[] ips;
		if (IPAddress.contains(".")) {
			ips = IPAddress.split("\\.");
			if (ips.length == 4) {
				for (String IPBlock : ips) {
					if (!is_ip_block(IPBlock)) {
						status = false;
						break;
					} else {
						status = true;
					}
				}
			}
		}
		return status;
	}

	/**
	 * @Date : 2020-12-08
	 * @param: ip_block
	 * @return: boolean
	 */
	public boolean is_ip_block(String ip_block) {
		int num = 0;
		boolean status = false;
		try {
			num = Integer.parseInt(ip_block);
			if (num <= 255) {
				status = true;
			}
		} catch (NumberFormatException e) {

		}
		return status;
	}

	public static void main(String[] args) {
		Utility utility = new Utility();
		// utility.removeTags("<p>data in </p>");
		// System.out.println("correct :" + utility.removeTags("<p>data in </p>Hello
		// <p>"));
		System.out.println("256 value :" + utility.isIPAddressCorrect("1.1.1.255"));
		System.out.println("String :" + utility.isIPAddressCorrect("1.1.1.ss"));
		System.out.println("wrong:" + utility.isIPAddressCorrect("1.1.1."));
		System.out.println("correct 255:" + utility.isIPAddressCorrect("255.255.255.255"));
	}

	public static String removeTags(String param) {
		// int startIndex = 0;
		// int endIndex = 0;
		String temp = param;
		// StringBuffer text;
		StringBuffer textTemp = new StringBuffer();
		boolean status = false;
		/* while (temp.contains("<") || temp.contains(">")) { */

		for (int index = 0; index < temp.length(); index++) {
			if (param.charAt(index) == '<') {
				// startIndex = index;
				status = true;
			} else if (temp.charAt(index) == '>') {
				// endIndex = index;
				status = false;
			}
			if (!status) {
				if (temp.charAt(index) != '>' && temp.charAt(index) != '<') {
					textTemp.append(param.charAt(index));
				}
			}

		}
		// System.out.println(textTemp.toString());
		/*
		 * text = new StringBuffer(temp); text.replace(startIndex, endIndex, temp); temp
		 * = text.toString();
		 */
		return textTemp.toString();
	}

	public static String timeStamp(String pattern) throws IllegalArgumentException {
		DateTimeFormatter formatter = null;

		try {
			formatter = DateTimeFormatter.ofPattern(pattern);
		} catch (IllegalArgumentException exp) {
			throw exp;
		}
		LocalDateTime dateTime = LocalDateTime.now();
		dateTime.format(formatter);
		return dateTime.toString();

	}

	public String MD5Encrypt(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(password.getBytes());
		BigInteger bigInt = new BigInteger(1, digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
}
