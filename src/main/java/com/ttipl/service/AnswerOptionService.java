package com.ttipl.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.pojo.Options;
import com.ttipl.pojo.Question;
import com.ttipl.repository.AnswerOptionRepo;

@Service
public class AnswerOptionService 
{
	@Autowired
	private AnswerOptionRepo answerOptionRepo;
	@Autowired
	private ExcelService service;

	public String importCodexlsx(MultipartFile filePart, List<Question> questions, String password) throws IOException
	{

		InputStream inputStream = null;
		XSSFWorkbook wb = null;
		int noOfCols = 0;
		Cell cell = null;
		Object questionNO = null;
		String answer = null;
		int rowCount = 0;
		List<String> answers = null;
		List<Options> options = null;

		inputStream = filePart.getInputStream();
		if (inputStream != null) 
		{
			if (password != null && !password.equalsIgnoreCase("")) 
			{
				try
				{
					inputStream = service.verifyExcelPassword(inputStream, password);
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
			if ((sheet.getPhysicalNumberOfRows() - 1) == questions.size())
			{
				options = new ArrayList<Options>();
				for (Row row : sheet)
				{
					if (rowCount >= 1) 
					{
						questionNO = null;
						answer = null;

						for (int i = 0; i < 2; i++)
						{
							cell = row.getCell(i);

							if (i == 0) 
							{
								if (cell.getCellType() == CellType.NUMERIC) 
								{
									questionNO = (int) cell.getNumericCellValue();
								} 
								else if (cell.getCellType() == CellType.STRING) 
								{
									questionNO = cell.getStringCellValue();
								}
							}
							if (i == 1) 
							{
								if (cell.getCellType() == CellType.NUMERIC)
								{
									answer = cell.getNumericCellValue() + "";
								} 
								else if (cell.getCellType() == CellType.STRING)
								{
									answer = cell.getStringCellValue();
									answer = answer.trim();
								}
							}
						}

						Options option = null;
						answers = new ArrayList<String>();

						if (answer.contains(",")) 
						{
							for (String ans : answer.split(",")) 
							{
								answers.add(ans);
							}
						} 
						else 
						{
							answers.add(answer);
						}
						for (String answer_current : answers) 
						{
							switch (answer_current.toUpperCase())
							{
							
							case "A"://1
								option = questions.get((rowCount - 1)).getOptions().get(0);
								option.setAnswer(true);
								options.add(option);
								break;
							case "B"://2
								option = questions.get((rowCount - 1)).getOptions().get(1);
								option.setAnswer(true);
								options.add(option);
								break;
							case "C"://3
								option = questions.get((rowCount - 1)).getOptions().get(2);
								option.setAnswer(true);
								options.add(option);
								break;
							case "D"://4
								option = questions.get((rowCount - 1)).getOptions().get(3);
								option.setAnswer(true);
								options.add(option);
								break;

							default:
								return "  <span class=\"font-red\">Answer option \" " + answer
										+ " \" unBounded for Answer <span>";

							}
						}

					} 
					else
					{
						noOfCols = row.getPhysicalNumberOfCells();
						if (noOfCols == 2) {
						} 
						else 
						{
							wb.close();
							return " <span class=\"font-red\">Cell Count  Not Matched!. Please Refer The AnswerKey Format <span>";
						}

					}

					rowCount++;

				}
			} 
			else
			{
				wb.close();
				return "<span class=\"font-red\">Questins And answers Count MisMatched  <span>";
			}

			wb.close();
			answerOptionRepo.saveAll(options);
			return " <span class=\"font-green\">Successfully uploaded <span>";
		} 
		else 
		{
			return "<span class=\"font-red\">Input strean can't be null<span>";
		}
	}
	public int deleteQuestions(int i) 
	{
		int sd = answerOptionRepo.deleteByExamId(i);
		return sd;
	}		
}
