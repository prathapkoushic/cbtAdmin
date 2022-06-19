
package com.ttipl.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ttil.encryption.EncryptionDecryption;
import com.ttipl.pojo.DescriptiveQuestion;
import com.ttipl.pojo.DescriptiveQuestionImages;
import com.ttipl.pojo.ExamBean;
import com.ttipl.pojo.Options;
import com.ttipl.pojo.Post;
import com.ttipl.pojo.Question;
import com.ttipl.pojo.QuestionImages;
import com.ttipl.pojo.ReportBean;

@Component
public class ExcelService 
{

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ExamService examService;

	public byte[] doExcelFileReport(List<ReportBean> list, Post post) throws IOException 
	{
		int rowIndex = 1;
		int colIndex = 7;
		int switchEle = 0;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = workbook.createSheet("Data");

		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mmm-dd"));
		// cellStyle.setWrapText(true);

		xssfSheet.setColumnWidth(0, 1200);
		xssfSheet.setColumnWidth(1, 2000);
		xssfSheet.setColumnWidth(2, 4500);
		xssfSheet.setColumnWidth(3, 3500);
		xssfSheet.setColumnWidth(4, 3000);
		xssfSheet.setColumnWidth(5, 3500);
		xssfSheet.setColumnWidth(6, 2500);
		xssfSheet.setColumnWidth(7, 5500);

		Row row = xssfSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);

		row.createCell(0).setCellValue("S.No");
		row.createCell(1).setCellValue("Roll NO");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("DOB");
		row.createCell(4).setCellValue("Total Marks");
		row.createCell(5).setCellValue("Community");
		row.createCell(6).setCellValue("Trade");
		row.createCell(7).setCellValue("Session");

		for (ReportBean entity : list) {
			row = xssfSheet.createRow(rowIndex);
			row.setRowStyle(cellStyle);
			// row.setHeightInPoints((2 ,xssfSheet.getDefaultRowHeightInPoints()));
			for (int cellIndex = 0; cellIndex <= colIndex; cellIndex++) {
				switchEle = cellIndex;
				cell = row.createCell(cellIndex);

				switch (switchEle) {
				case 0:
					cell.setCellType(CellType.NUMERIC);
					// cell.setCellStyle(cellStyle);
					cell.setCellValue(rowIndex);
					break;

				case 1:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getHTnumber());
					break;

				case 2:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCandidateName());
					break;

				case 3:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getDob());
					break;
				case 4:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalObtained());
					break;
				case 5:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCommunity());
					break;
				case 6:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(post.getPost_name());
					break;
				case 7:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getLocationName() + "(" + entity.getSession() + ")");
					break;

				}

			}
			rowIndex++;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
		} catch (Exception e) {
		} finally {
			bos.close();
			workbook.close();
		}

		return bos.toByteArray();
	}

	public byte[] doExcelFileDetailedReport(List<ReportBean> list, ExamBean exam) throws IOException 
	{
		int rowIndex = 1;
		int colIndex = 12;
		int switchEle = 0;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = workbook.createSheet("Sheet 0");

		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mmm-dd"));
		// cellStyle.setWrapText(true);

		xssfSheet.setColumnWidth(0, 1200);
		xssfSheet.setColumnWidth(1, 2000);
		xssfSheet.setColumnWidth(2, 4500);
		xssfSheet.setColumnWidth(3, 3500);
		xssfSheet.setColumnWidth(4, 6000);
		xssfSheet.setColumnWidth(5, 3500);
		xssfSheet.setColumnWidth(6, 3500);
		xssfSheet.setColumnWidth(7, 5500);
		xssfSheet.setColumnWidth(8, 3500);
		xssfSheet.setColumnWidth(9, 3500);
		xssfSheet.setColumnWidth(10, 5500);
		xssfSheet.setColumnWidth(11, 3500);

		Row row = xssfSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);

		row.createCell(0).setCellValue("S.No");
		row.createCell(1).setCellValue("Roll NO");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("DOB");
		row.createCell(4).setCellValue("Responses");
		row.createCell(5).setCellValue("Attempted");
		row.createCell(6).setCellValue("Total Correct");
		row.createCell(7).setCellValue("Total Negetive Marks");
		row.createCell(8).setCellValue("Total Marks");
		row.createCell(9).setCellValue("Community");
		row.createCell(10).setCellValue("Exam Code");
		row.createCell(11).setCellValue("Session");

		for (ReportBean entity : list)
		{
			row = xssfSheet.createRow(rowIndex);
			row.setRowStyle(cellStyle);
			for (int cellIndex = 0; cellIndex <= colIndex; cellIndex++)
			{
				switchEle = cellIndex;
				cell = row.createCell(cellIndex);

				switch (switchEle)
				{
				case 0:
					cell.setCellType(CellType.NUMERIC);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(rowIndex);
					break;

				case 1:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getHTnumber());
					break;

				case 2:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCandidateName());
					break;

				case 3:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getDob());
					break;
				case 4:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getResponses());
					break;
				case 5:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalAnswered());
					break;
				case 6:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalCorrect());
					break;
				case 7:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getNegativeMarks());
					break;
				case 8:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getTotalObtained());
					break;
				case 9:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getCommunity());
					break;
				case 10:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(exam.getExam_code());
					break;
				case 11:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getLocationName() + "(" + entity.getSession() + ")");
					break;

				}

			}
			rowIndex++;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try
		{
			workbook.write(bos);
		} 
		catch (Exception e) {} 
		finally
		{
			bos.close();
			workbook.close();
		}

		return bos.toByteArray();
	}

	public String ExtractQuestionsFromExcel(MultipartFile file, int examId, String setNo,int totalQuestions, int remainingQueWeWant,
			int examLocSessionId,  String password) throws Exception
	{
		InputStream inputStream = file.getInputStream();
		List<Question> questions = new ArrayList<Question>();

		XSSFWorkbook wb = null;
		if (inputStream != null) 
		{
			if (password != null && !password.equalsIgnoreCase(""))
			{
				try
				{
					inputStream = verifyExcelPassword(inputStream, password);
					if (inputStream != null)
					{
						return " <span class=\"font-red\">Invalid Password</span>";
					}
				} 
				catch (GeneralSecurityException e) 
				{
					return " <span class=\"font-red\">Internal server error</span>";
				} 
				catch (OfficeXmlFileException e) 
				{
					return " <span class=\"font-red\">Please check excel password protected or not</span>";
				}
			}
			try 
			{
				wb = new XSSFWorkbook(inputStream);
			} 
			catch (OfficeXmlFileException e) 
			{
				return " <span class=\"font-red\">Please check excel password protected or not</span>";
			}

			XSSFSheet sheet = wb.getSheetAt(0);

			int noOfCols = 0;

			XSSFDrawing dp = sheet.createDrawingPatriarch();
			List<XSSFShape> pics = dp.getShapes();
			int image_coulmn = 0, image_row = 0;
			XSSFPicture inpPic = null;
			XSSFClientAnchor clientAnchor = null;
			
			  Cell cell = null;
			String question_eng = "", option_eng_1 = "", option_eng_2 = "", option_eng_3 = "", option_eng_4 = "",
					question_hindi = "", option_hindi_1 = "", option_hindi_2 = "", option_hindi_3 = "",
					option_hindi_4 = "";
			String question_urdu = "", option_urdu_1 = "", option_urdu_2 = "", option_urdu_3 = "", option_urdu_4 = "",
					question_tamil = "", option_tamil_1 = "", option_tamil_2 = "", option_tamil_3 = "",
					option_tamil_4 = "";
			String question_marathi = "", option_marathi_1 = "", option_marathi_2 = "", option_marathi_3 = "",
					option_marathi_4 = "", question_telugu = "", option_telugu_1 = "", option_telugu_2 = "",
					option_telugu_3 = "", option_telugu_4 = "";
			String question_kanada = "", option_kanada_1 = "", option_kanada_2 = "", option_kanada_3 = "",
					option_kanada_4 = "";

			
			int rowCount = 0;
			int totalRows = (sheet.getPhysicalNumberOfRows() - 1);
			
			
			
			 if (totalRows >= remainingQueWeWant)
			{
				for (Row row : sheet) 
					
				{
					if(row.getRowNum()<=remainingQueWeWant)
					{
					if (rowCount >= 1) {
						question_eng = "";
						option_eng_1 = "";
						option_eng_2 = "";
						option_eng_3 = "";
						option_eng_4 = "";
						question_hindi = "";
						option_hindi_1 = "";
						option_hindi_2 = "";
						option_hindi_3 = "";
						option_hindi_4 = "";
						image_coulmn = 0;
						image_row = 0;
						question_urdu = "";
						option_urdu_1 = "";
						option_urdu_2 = "";
						option_urdu_3 = "";
						option_urdu_4 = "";
						question_tamil = "";
						option_tamil_1 = "";
						option_tamil_2 = "";
						option_tamil_3 = "";
						option_tamil_4 = "";
						question_marathi = "";
						option_marathi_1 = "";
						option_marathi_2 = "";
						option_marathi_3 = "";
						option_marathi_4 = "";
						question_telugu = "";
						option_telugu_1 = "";
						option_telugu_2 = "";
						option_telugu_3 = "";
						option_telugu_4 = "";
						question_kanada = "";
						option_kanada_1 = "";
						option_kanada_2 = "";
						option_kanada_3 = "";
						option_kanada_4 = "";
						inpPic = null;
						clientAnchor = null;
						Boolean blank = false;
						int rowNo = 0;
						int cellNo = 0;

						noOfCols = row.getPhysicalNumberOfCells();
						if ( noOfCols>=0) 
						{
							for (int i = 0; i < noOfCols; i++) 
							{
								cell = row.getCell(i);
								if (i == 0) 
								{
									
								}
								
								if (i == 1) 
								{
									question_eng = this.getValueFromCell(cell, question_eng);
									if(question_eng=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								}

								else if (i == 2) {
									option_eng_1 = this.getValueFromCell(cell, option_eng_1);
									if(option_eng_1=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 3) {
									option_eng_2 = this.getValueFromCell(cell, option_eng_2);
									if(option_eng_2=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}


								} else if (i == 4) {
									option_eng_3 = this.getValueFromCell(cell, option_eng_3);
									if(option_eng_3=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 5) {
									option_eng_4 = this.getValueFromCell(cell, option_eng_4);
									if(option_eng_4=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								}

								else if (i == 6) { 
									question_hindi = this.getValueFromCell(cell, question_hindi);
									if(question_hindi=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 7) {
									option_hindi_1 = this.getValueFromCell(cell, option_hindi_1);
									if(option_hindi_1=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								} else if (i == 8) {
									option_hindi_2 = this.getValueFromCell(cell, option_hindi_2);
									if(option_hindi_2=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 9) {
									option_hindi_3 = this.getValueFromCell(cell, option_hindi_3);
									if(option_hindi_3=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 10) {
									option_hindi_4 = this.getValueFromCell(cell, option_hindi_4);
									if(option_hindi_4=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 11) {// urdu questions
									question_urdu = this.getValueFromCell(cell, question_urdu);
									if(question_urdu=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 12) {
									option_urdu_1 = this.getValueFromCell(cell, option_urdu_1);
									if(option_urdu_1=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 13) {
									option_urdu_2 = this.getValueFromCell(cell, option_urdu_2);
									if(option_urdu_2=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 14) {
									option_urdu_3 = this.getValueFromCell(cell, option_urdu_3);
									if(option_urdu_3=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 15) {
									option_urdu_4 = this.getValueFromCell(cell, option_urdu_4);
									if(option_urdu_4=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								}

								else if (i == 16) {// tamil questions
									question_tamil = this.getValueFromCell(cell, question_tamil);
									if(question_tamil=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								} else if (i == 17) {
									option_tamil_1 = this.getValueFromCell(cell, option_tamil_1);
									if(option_tamil_1=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 18) {
									option_tamil_2 = this.getValueFromCell(cell, option_tamil_2);
									if(option_tamil_2=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 19) {
									option_tamil_3 = this.getValueFromCell(cell, option_tamil_3);
									if(option_tamil_3=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 20) {
									option_tamil_4 = this.getValueFromCell(cell, option_tamil_4);
									if(option_tamil_4=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								}

								else if (i == 21) {// marathi questions
									question_marathi = this.getValueFromCell(cell, question_marathi);
									if(question_marathi=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 22) {
									option_marathi_1 = this.getValueFromCell(cell, option_marathi_1);
									if(option_marathi_1=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 23) {
									option_marathi_2 = this.getValueFromCell(cell, option_marathi_2);
									if(option_marathi_2=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 24) {
									option_marathi_3 = this.getValueFromCell(cell, option_marathi_3);
									if(option_marathi_3=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 25) {
									option_marathi_4 = this.getValueFromCell(cell, option_marathi_4);
									if(option_marathi_4=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 26) {// telugu questions
									question_telugu = this.getValueFromCell(cell, question_telugu);
									if(question_telugu=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 27) {
									option_telugu_1 = this.getValueFromCell(cell, option_telugu_1);
									if(option_telugu_1=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}


								} else if (i == 28) {
									option_telugu_2 = this.getValueFromCell(cell, option_telugu_2);
									if(option_telugu_2=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 29) {
									option_telugu_3 = this.getValueFromCell(cell, option_telugu_3);
									if(option_telugu_3=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 30) {
									option_telugu_4 = this.getValueFromCell(cell, option_telugu_4);
									if(option_telugu_4=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 31) {// tamil questions
									question_kanada = this.getValueFromCell(cell, question_kanada);
									if(question_kanada=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}

								} else if (i == 32) {
									option_kanada_1 = this.getValueFromCell(cell, option_kanada_1);
									if(option_kanada_1=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								} else if (i == 33) {
									option_kanada_2 = this.getValueFromCell(cell, option_kanada_2);
									if(option_kanada_2=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								} else if (i == 34) {
									option_kanada_3 = this.getValueFromCell(cell, option_kanada_3);
									if(option_kanada_3=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								} else if (i == 35) {
									option_kanada_4 = this.getValueFromCell(cell, option_kanada_4);
									if(option_kanada_4=="")
									{
										blank = true;
										rowNo = row.getRowNum()+1;
										cellNo = i+1;
									}
								}
								
								
							}
							if (rowCount >= 1)
							{
								Question question = new Question();
								if ((question_eng != null || question_eng != "")
										&& (question_eng != null || question_eng != ""))
								{
									question.setQuestionName(question_eng);
									question.setQuestionHindi(question_hindi);

									question.setQuestionTamil(question_tamil);
									question.setQuestionUrdu(question_urdu);
									question.setQuestionMarathi(question_marathi);
									question.setQuestionTelugu(question_telugu);
									question.setQuestionKanada(question_kanada);
									question.setQuestionType("Multiple Choice");
									question.setBankQuestionId(18);
									question.setExamLocSessionId(1);
									List<Options> options = new ArrayList<Options>();
									for (int i = 0; i <= 3; i++)
									{
										Options option = new Options();
										options.add(option);
									}
									if (pics != null && pics.size() > 0)
									{
										for (XSSFShape pic : pics)
										{
											
											if(!(pic instanceof XSSFPicture))
											{}
											
											
											else
											{
										        inpPic = (XSSFPicture) pic;
											
											clientAnchor = (XSSFClientAnchor) pic.getAnchor();
											image_coulmn = clientAnchor.getCol1();
											image_row = clientAnchor.getRow1();
											
											
											if (image_row == rowCount) 
											{
												String blob = null;

												inpPic.getShapeName();
												PictureData pict = inpPic.getPictureData();
												byte[] data = pict.getData();
												try
												{
													blob = Base64.getEncoder().encodeToString(data);
												}
												catch (Exception e) 
												{
													e.printStackTrace();
												}

												if (image_coulmn == 0) {
												}
												if (image_coulmn == 1) {
													blank = false;
													question.setEnglishImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 2) {
													blank = false;
													options.get(0).setEnglishImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 3) {
													blank = false;
													options.get(1).setEnglishImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 4) {
													blank = false;
													options.get(2).setEnglishImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 5) {
													blank = false;
													options.get(3).setEnglishImageId(this.saveImageFromBlob(blob));
												}
												// setting hindi images
												if (image_coulmn == 6) {
													blank = false;
													question.setHindiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 7) {
													blank = false;
													options.get(0).setHindiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 8) {
													blank = false;
													options.get(1).setHindiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 9) {
													blank = false;
													options.get(2).setHindiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 10) {
													blank = false;
													options.get(3).setHindiImageId(this.saveImageFromBlob(blob));
												}

												// setting urdu images
												if (image_coulmn == 11) {
													blank = false;
													question.setUrduImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 12) {
													blank = false;
													options.get(0).setUrduImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 13) {
													blank = false;
													options.get(1).setUrduImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 14) {
													blank = false;
													options.get(2).setUrduImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 15) {
													blank = false;
													options.get(3).setUrduImageId(this.saveImageFromBlob(blob));
												}

												if (image_coulmn == 16) {
													blank = false;
													question.setTamilImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 17) {
													blank = false;
													options.get(0).setTamilImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 18) {
													blank = false;
													options.get(1).setTamilImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 19) {
													blank = false;
													options.get(2).setTamilImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 20) {
													blank = false;
													options.get(3).setTamilImageId(this.saveImageFromBlob(blob));
												}

												// setting marathi questions
												if (image_coulmn == 21) {
													blank = false;
													question.setMarathiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 22) {
													blank = false;
													options.get(0).setMarathiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 23) {
													blank = false;
													options.get(1).setMarathiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 24) {
													blank = false;
													options.get(2).setMarathiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 25) {
													blank = false;
													options.get(3).setMarathiImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 26) {
													blank = false;
													question.setTeluguImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 27) {
													blank = false;
													options.get(0).setTeluguImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 28) {
													blank = false;
													options.get(1).setTeluguImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 29) {
													blank = false;
													options.get(2).setTeluguImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 30) {
													blank = false;
													options.get(3).setTeluguImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 31) {
													blank = false;
													question.setKanadaImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 32) {
													blank = false;
													options.get(0).setKanadaImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 33) {
													blank = false;
													options.get(1).setKanadaImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 34) {
													blank = false;
													options.get(2).setKanadaImageId(this.saveImageFromBlob(blob));
												}
												if (image_coulmn == 35) {
													blank = false;
													options.get(3).setKanadaImageId(this.saveImageFromBlob(blob));
												}
											}
											}
										}
									}
									// option 1 setter

									options.get(0).setAnswerOptionEnglish(option_eng_1);
									options.get(0).setAnswerOptionHindi(option_hindi_1);
									options.get(0).setAnswerOptionTamil(option_tamil_1);
									options.get(0).setAnswerOptionTelugu(option_telugu_1);
									options.get(0).setAnswerOptionUrdu(option_urdu_1);
									options.get(0).setAnswerOptionMarathi(option_marathi_1);
									options.get(0).setAnswerOptionKanada(option_kanada_1);

									options.get(0).setOptionOrder(1);
									options.get(0).setQuestion(question);
									// option 2 setters
									options.get(1).setAnswerOptionEnglish(option_eng_2);
									options.get(1).setAnswerOptionHindi(option_hindi_2);
									options.get(1).setAnswerOptionTamil(option_tamil_2);
									options.get(1).setAnswerOptionTelugu(option_telugu_2);
									options.get(1).setAnswerOptionUrdu(option_urdu_2);
									options.get(1).setAnswerOptionMarathi(option_marathi_2);
									options.get(1).setAnswerOptionKanada(option_kanada_2);
									options.get(1).setOptionOrder(2);
									options.get(1).setQuestion(question);

									// option 3 setters
									options.get(2).setAnswerOptionEnglish(option_eng_3);
									options.get(2).setAnswerOptionHindi(option_hindi_3);
									options.get(2).setAnswerOptionTamil(option_tamil_3);
									options.get(2).setAnswerOptionTelugu(option_telugu_3);
									options.get(2).setAnswerOptionUrdu(option_urdu_3);
									options.get(2).setAnswerOptionMarathi(option_marathi_3);
									options.get(2).setAnswerOptionKanada(option_kanada_3);
									options.get(2).setOptionOrder(3);
									options.get(2).setQuestion(question);

									// option 3 setters
									options.get(3).setAnswerOptionEnglish(option_eng_4);
									options.get(3).setAnswerOptionHindi(option_hindi_4);
									options.get(3).setAnswerOptionTamil(option_tamil_4);
									options.get(3).setAnswerOptionTelugu(option_telugu_4);
									options.get(3).setAnswerOptionUrdu(option_urdu_4);
									options.get(3).setAnswerOptionMarathi(option_marathi_4);
									options.get(3).setAnswerOptionKanada(option_kanada_4);
									options.get(3).setOptionOrder(4);
									options.get(3).setQuestion(question);

									question.setExamId(examId);
									question.setSetNo(setNo);
									question.setOptions(options);
									questions.add(question);
									
									if(blank==true)
									{
										wb.close();
										return "<span class=\"font-red\">At ROW "+rowNo+" and at cell "+cellNo+" we are not finding data</span>";
									}
								} 
								else 
								{
									wb.close();
									return "  <span class=\"font-red\">We found excel sheet rows (" + totalRows
											+ ") and It should not be blank or empty </span>";
								}
							}
						}
						else 
						{
							wb.close();
							return "  <span class=\"font-red\">Cell count not matched. Please refer question sheet format </span>";
						}
					}
					rowCount++;

				}
				}
			} 
			else
			{
				wb.close();
				return "  <span class=\"font-red\">Questions size exceeds the max limit. In excel sheet have "
						+ totalRows + " questions But we need only " + remainingQueWeWant + "  </span>";

			}
		}
		wb.close();
		ExamBean examBean= examService.findByExamId(examId);
		String examName = examBean.getExam_name();
		return saveAllQuestoionds(questions, examLocSessionId , examId , examName);
	}
	
	
	
	public String ExtractQuestionsFromExcel2(MultipartFile file, int examId, String setNo,int totalQuestions, int remainingQueWeWant,
			int examLocSessionId,  String password, String level) throws Exception
	{
		InputStream inputStream = file.getInputStream();
		List<DescriptiveQuestion> questions = new ArrayList<DescriptiveQuestion>();

		XSSFWorkbook wb = null;
		if (inputStream != null) 
		{
			if (password != null && !password.equalsIgnoreCase(""))
			{
				try
				{
					inputStream = verifyExcelPassword(inputStream, password);
					if (inputStream != null)
					{
						return " <span class=\"font-red\">Invalid Password</span>";
					}
				} 
				catch (GeneralSecurityException e) 
				{
					return " <span class=\"font-red\">Internal server error</span>";
				} 
				catch (OfficeXmlFileException e) 
				{
					return " <span class=\"font-red\">Please check excel password protected or not</span>";
				}
			}
			try 
			{
				wb = new XSSFWorkbook(inputStream);
			} 
			catch (OfficeXmlFileException e) 
			{
				return " <span class=\"font-red\">Please check excel password protected or not</span>";
			}

			XSSFSheet sheet = wb.getSheetAt(0);

			int noOfCols = 0;

			XSSFDrawing dp = sheet.createDrawingPatriarch();
			List<XSSFShape> pics = dp.getShapes();
			int image_coulmn = 0, image_row = 0;
			XSSFPicture inpPic = null;
			XSSFClientAnchor clientAnchor = null;
			// boolean imageFlag = false;
			/*
			 * if (pics != null && pics.size() > 0) { for (XSSFShape pic : pics) { //
			 * XSSFPicture inpPic = (XSSFPicture)pics.get(0); inpPic = (XSSFPicture) pic;
			 * clientAnchor = inpPic.getClientAnchor(); image_coulmn =
			 * clientAnchor.getCol1(); image_row = clientAnchor.getRow1();
			 * 
			 * } for (XSSFShape pic : pics) { // XSSFPicture inpPic =
			 * (XSSFPicture)pics.get(0); inpPic = (XSSFPicture) pic; clientAnchor =
			 * inpPic.getClientAnchor(); inpPic.getShapeName(); PictureData pict =
			 * inpPic.getPictureData(); FileOutputStream out = new
			 * FileOutputStream("pict.jpg");
			 * 
			 * byte[] data = pict.getData(); try { Blob blob = new SerialBlob(data);
			 * System.out.println(blob); } catch (SQLException e) { e.printStackTrace(); }
			 * System.out.println(data); out.write(data); out.close();
			 * System.out.println("col1: " + clientAnchor.getCol1() + ", col2: " +
			 * clientAnchor.getCol2() + ", row1: " + clientAnchor.getRow1() + ", row2: " +
			 * clientAnchor.getRow2()); System.out.println("x1: " + clientAnchor.getDx1() +
			 * ", x2: " + clientAnchor.getDx2() + ", y1: " + clientAnchor.getDy1() +
			 * ", y2: " + clientAnchor.getDy2());
			 * 
			 * } }
			 * 
			 * 
			 * List<XSSFPictureData> lst = wb.getAllPictures();
			 * 
			 * for (XSSFPictureData pict1 : lst) {
			 * 
			 * // PictureData pict = (PictureData)it.next(); String ext =
			 * pict.suggestFileExtension(); byte[] data1 = pict.getData(); if
			 * (ext.equalsIgnoreCase("JPG")) { try (OutputStream out = new
			 * FileOutputStream("C:\\Users\\DELL\\Desktop\\cbt_demo\\pict.jpg")) {
			 * out.write(data); } }
			 * 
			 * }
			 * 
			 * 
			 * // ---------------
			 */ Cell cell = null;
			String question_eng = "", option_eng_1 = "", option_eng_2 = "", option_eng_3 = "", option_eng_4 = "",
					question_hindi = "", option_hindi_1 = "", option_hindi_2 = "", option_hindi_3 = "",
					option_hindi_4 = "";
			String question_urdu = "", option_urdu_1 = "", option_urdu_2 = "", option_urdu_3 = "", option_urdu_4 = "",
					question_tamil = "", option_tamil_1 = "", option_tamil_2 = "", option_tamil_3 = "",
					option_tamil_4 = "";
			String question_marathi = "", option_marathi_1 = "", option_marathi_2 = "", option_marathi_3 = "",
					option_marathi_4 = "", question_telugu = "", option_telugu_1 = "", option_telugu_2 = "",
					option_telugu_3 = "", option_telugu_4 = "";
			String question_kanada = "", option_kanada_1 = "", option_kanada_2 = "", option_kanada_3 = "",
					option_kanada_4 = "";

			/*
			 * String question_eng_blob = null, option_eng_1_blob = null, option_eng_2_blob
			 * = null, option_eng_3_blob = null, option_eng_4_blob = null,
			 * question_hindi_blob = null, option_hindi_1_blob = null, option_hindi_2_blob =
			 * null, option_hindi_3_blob = null, option_hindi_4_blob = null;
			 */
			int rowCount = 0;
			int totalRows = (sheet.getPhysicalNumberOfRows() - 1);
			
			
			 if (totalRows>0)
			{
				for (Row row : sheet) 
					
				{
					if (rowCount >= 1) {
						question_eng = "";
						option_eng_1 = "";
						option_eng_2 = "";
						option_eng_3 = "";
						option_eng_4 = "";
						question_hindi = "";
						option_hindi_1 = "";
						option_hindi_2 = "";
						option_hindi_3 = "";
						option_hindi_4 = "";
						image_coulmn = 0;
						image_row = 0;
						question_urdu = "";
						option_urdu_1 = "";
						option_urdu_2 = "";
						option_urdu_3 = "";
						option_urdu_4 = "";
						question_tamil = "";
						option_tamil_1 = "";
						option_tamil_2 = "";
						option_tamil_3 = "";
						option_tamil_4 = "";
						question_marathi = "";
						option_marathi_1 = "";
						option_marathi_2 = "";
						option_marathi_3 = "";
						option_marathi_4 = "";
						question_telugu = "";
						option_telugu_1 = "";
						option_telugu_2 = "";
						option_telugu_3 = "";
						option_telugu_4 = "";
						question_kanada = "";
						option_kanada_1 = "";
						option_kanada_2 = "";
						option_kanada_3 = "";
						option_kanada_4 = "";
						inpPic = null;
						clientAnchor = null;
						// imageFlag = false;
						/*
						 * question_eng_blob = null; option_eng_1_blob = null; option_eng_2_blob = null;
						 * option_eng_3_blob = null; option_eng_4_blob = null; question_hindi_blob =
						 * null; option_hindi_1_blob = null; option_hindi_2_blob = null;
						 * option_hindi_3_blob = null; option_hindi_4_blob = null;
						 */

						noOfCols = row.getPhysicalNumberOfCells();
						if ( noOfCols>=0) 
						{
							for (int i = 0; i < noOfCols; i++) 
							{
								cell = row.getCell(i);
								if (i == 0) 
								{
									
								}
								
								if (i == 1) 
								{
									question_eng = this.getValueFromCell(cell, question_eng);
								}

								else if (i == 2) 
								{ 
									question_hindi = this.getValueFromCell(cell, question_hindi);
								}
								
							}
							if (rowCount >= 1)
							{
								DescriptiveQuestion question = new DescriptiveQuestion();
								if ((question_eng != null || question_eng != "")
										&& (question_eng != null || question_eng != ""))
								{
									question.setQuestionName(question_eng);
									question.setQuestionHindi(question_hindi);
                                    question.setLevel(level);									
									question.setQuestionType("DESCRIPTIVE");
									question.setExamLocSessionId(1);
									
									if (pics != null && pics.size() > 0)
									{
										for (XSSFShape pic : pics)
										{
											
											if(!(pic instanceof XSSFPicture))
											{}
											
											
											else
											{
												inpPic = (XSSFPicture) pic;
											
											clientAnchor = (XSSFClientAnchor) pic.getAnchor();
											image_coulmn = clientAnchor.getCol1();
											image_row = clientAnchor.getRow1();
											
											
											if (image_row == rowCount) 
											{
												String blob = null;

												inpPic.getShapeName();
												PictureData pict = inpPic.getPictureData();
												byte[] data = pict.getData();
												try
												{
													blob = Base64.getEncoder().encodeToString(data);
												}
												catch (Exception e) 
												{
													e.printStackTrace();
												}

												if (image_coulmn == 0) {
												}
												if (image_coulmn == 1) {
													question.setEnglishImageId(this.saveImageFromBlob2(blob));
												}
												
												if (image_coulmn == 2) {
													question.setHindiImageId(this.saveImageFromBlob2(blob));
												}
												
											}
											}
										}
									}
								

									question.setExamId(examId);
									question.setSetNo(setNo);
									questions.add(question);
								} 
								else 
								{
									wb.close();
									return "  <span class=\"font-red\">We found excel sheet rows (" + totalRows
											+ ") and It should not be blank or empty </span>";
								}
							}
						}
						else 
						{
							wb.close();
							return "  <span class=\"font-red\">Cell count not matched. Please refer question sheet format </span>";
						}
					}
					rowCount++;

				}
			} 
			else
			{
				wb.close();
				return "  <span class=\"font-red\">Questions size exceeds the max limit. In excel sheet have "
						+ totalRows + " questions But we need only " + remainingQueWeWant + "  </span>";

			}
		}
		wb.close();
		ExamBean examBean= examService.findByExamId(examId);
		String examName = examBean.getExam_name();
		return saveAllQuestoionds2(questions, examLocSessionId , examId , examName);
	}

	public String saveAllQuestoionds(List<Question> questions, int examLocSessionId, int examId, String examName) throws Exception 
	{
		
		if (questions != null)
		{
			for (Question question : questions) 
			{
				question.setExamLocSessionId(examLocSessionId);
				//questionService.saveQuestion(question);
			}
			questionService.saveQuestion(questions , examId);
			return "<span class=\"font-green\">Successfully ( \"" + questions.size()
					+ "\" ) questions uploaded for Exam '<span style='color:red'>"+ examName +"</span>'</span>";
		} else {
			return " <span class=\"font-red\">Internal server error</span>";
		}
	}
	
	
	public String saveAllQuestoionds2(List<DescriptiveQuestion> questions, int examLocSessionId, int examId , String examName) throws Exception 
	{
		if (questions != null)
		{
			for (DescriptiveQuestion question : questions) 
			{
				question.setExamLocSessionId(examLocSessionId);
				//questionService.saveQuestion(question);
			}
			questionService.saveDescriptiveQuestion(questions , examId);
			return "<span class=\"font-green\">Successfully ( \"" + questions.size()
					+ "\" ) questions uploaded for Exam '<span style='color:red'>"+ examName +"</span>'</span>";
		} else {
			return " <span class=\"font-red\">Internal server error</span>";
		}
	}

	public InputStream verifyExcelPassword(InputStream inputStream, String password)
			throws IOException, GeneralSecurityException 
	{
		POIFSFileSystem fs = null;

		fs = new POIFSFileSystem(inputStream);
		EncryptionInfo info = new EncryptionInfo(fs);
		Decryptor d = Decryptor.getInstance(info);
		if (d.verifyPassword("password")) 
		{
			return d.getDataStream(fs);
		}
		else 
		{
			return null;
		}

	}

	public String getValueFromCell(Cell cell, String column) {
		if (cell.getCellType() == CellType.STRING) {
			column = cell.getStringCellValue();
			column = column.replaceAll("'", "");
			column = column.trim();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			DataFormatter fmt = new DataFormatter();
			column = fmt.formatCellValue(cell);

		}
		return column;

	}

	public int saveImageFromBlob2(String blob)
	{
		DescriptiveQuestionImages questionImages = new DescriptiveQuestionImages();
		questionImages.setImage(blob);
		return questionService.saveDesImage(questionImages).getImageId();
	}
	
	public int saveImageFromBlob(String blob)
	{
		QuestionImages questionImages = new QuestionImages();
		questionImages.setImage(blob);
		return questionService.saveImage(questionImages).getImageId();
	}

	public byte[] doExcelOfQuestion(List<Object[]> objects) throws Exception
	{
		
		int rowIndex = 1;
		int colIndex = 4;
		int switchEle = 0;
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet xssfSheet = workbook.createSheet("Sheet 0");

		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mmm-dd"));

		xssfSheet.setColumnWidth(0, 1200);
		xssfSheet.setColumnWidth(1, 5000);
		xssfSheet.setColumnWidth(2, 7500);
		xssfSheet.setColumnWidth(3, 3500);
		
		Row row = xssfSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellStyle(cellStyle);

		row.createCell(0).setCellValue("S.No");
		row.createCell(1).setCellValue("Question");
		row.createCell(2).setCellValue("Candidate Answer");
		row.createCell(3).setCellValue("Correct Answer");
		

		for (Object[] entity : objects)
		{
			
			row = xssfSheet.createRow(rowIndex);
			row.setRowStyle(cellStyle);
			for (int cellIndex = 0; cellIndex <= colIndex; cellIndex++)
			{
				switchEle = cellIndex;
				cell = row.createCell(cellIndex);

				switch (switchEle)
				{
				case 0:
					cell.setCellType(CellType.NUMERIC);
					//cell.setCellStyle(cellStyle);
					cell.setCellValue(rowIndex);
					break;

				case 1:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(EncryptionDecryption.decrypt((String)entity[3]));
					break;

				case 2:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue((String) entity[4]);
					break;
					
				case 3:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue((String) entity[6]);
					break;

				}

			}
			rowIndex++;
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try
		{
			workbook.write(bos);
		} 
		catch (Exception e) {} 
		finally
		{
			bos.close();
			workbook.close();
		}

		return bos.toByteArray();
	}

}
