package com.ttipl.util;

public class ControllerDTO
{
	// private String filePath;
	// private String encodedString;

	private Integer uploaded;
	private String fileName;
	private String url;
	private String responseText;

	public Integer getUploaded() {
		return uploaded;
	}

	public void setUploaded(Integer uploaded) {
		this.uploaded = uploaded;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

}