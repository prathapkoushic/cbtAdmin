package com.ttipl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttil.encryption.EncryptionDecryption;
import com.ttipl.pojo.DescriptiveQuestion;
import com.ttipl.pojo.DescriptiveQuestionImages;
import com.ttipl.pojo.ExamQuestions;
import com.ttipl.pojo.Question;
import com.ttipl.pojo.QuestionImages;
import com.ttipl.repository.DescriptiveQuestionImagesRepo;
import com.ttipl.repository.DescriptiveQuestionRepo;
import com.ttipl.repository.ExamQuestionRepository;
import com.ttipl.repository.ExamRepository;
import com.ttipl.repository.QuestionImageRepo;
import com.ttipl.repository.QuestionRepo;

@Service
@Transactional
public class QuestionService 
{
	@Autowired
	private QuestionRepo repo;
	@Autowired
	private ExamQuestionRepository examQuestionRepository;
	@Autowired
	private DescriptiveQuestionRepo desrepo;
	@Autowired
	private QuestionImageRepo imagerRpo;
	@Autowired
	private DescriptiveQuestionImagesRepo desImagerRpo;
	@Autowired
	private ExamRepository examRepo;

	public QuestionImages saveImage(QuestionImages question) 
	{
		return imagerRpo.save(question);
	}
	
	public DescriptiveQuestionImages saveDesImage(DescriptiveQuestionImages question) 
	{
		return desImagerRpo.save(question);
	}

	public String findByImageId(int id) {
		return imagerRpo.findByImageId(id);
	}

	public String findByImageId2(int id) {
		return desImagerRpo.findByImageId(id);
	}
	
	public Question saveQuestion(Question question) throws SQLException 
	{  
		Question q = repo.save(question);
		 return q;
    }

	@Transactional(rollbackFor = SQLException.class)
	public List<Question> saveQuestion(List<Question> questions, int examId) throws SQLException 
	{  
		 List<Question> list2 = (List<Question>) repo.saveAll(questions);
		  return list2;
		
    }
	
	@Transactional(rollbackFor = SQLException.class)
	public List<DescriptiveQuestion> saveDescriptiveQuestion(List<DescriptiveQuestion> questions, int examId) throws SQLException 
	{  
		 List<DescriptiveQuestion> list2 = (List<DescriptiveQuestion>) desrepo.saveAll(questions);
		  return list2;
		
    }


	public Question findQuestionById(int id) {
		return repo.findByQuestionId(id);
	}

	public int deleteQuestionByexamLocSessionId(int id) {
		return repo.deleteQuestionByexamLocSessionId(id);
	}

	public List<Question> findAll() {
		return repo.findAll();
	}

	public List<Question> findBySetNoAndExamId(String setNo, int examId) {
		return repo.findBySetNoAndExamId(setNo, examId);

	}

	public int findCountBySetNoAndExamId(String setNo, int examId) {
		return repo.findCountBySetNoAndExamId(setNo, examId);

	}
	
	public int findCountBySetNoAndExamId2(String setNo, int examId) {
		return desrepo.findCountBySetNoAndExamId2(setNo, examId);

	}

	public void doExcelFileReport(List<Question> list) throws IOException {
		int rowIndex = 1;
		int colIndex = 12;
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

		row.createCell(0).setCellValue("Question");
		row.createCell(1).setCellValue("option1");
		row.createCell(2).setCellValue("option2");
		row.createCell(3).setCellValue("option3");
		row.createCell(4).setCellValue("option4");
		row.createCell(5).setCellValue("Question_hindi");
		row.createCell(6).setCellValue("option_hindi_1");
		row.createCell(7).setCellValue("option_hindi_2");
		row.createCell(8).setCellValue("option_hindi_3");
		row.createCell(9).setCellValue("option_hindi_4");
		row.createCell(10).setCellValue("QuestionID");
		row.createCell(11).setCellValue("answer_option");
		for (Question entity : list) {
			row = xssfSheet.createRow(rowIndex);
			row.setRowStyle(cellStyle);
			// row.setHeightInPoints((2 ,xssfSheet.getDefaultRowHeightInPoints()));
			for (int cellIndex = 0; cellIndex <= colIndex; cellIndex++) {
				switchEle = cellIndex;
				cell = row.createCell(cellIndex);

				switch (switchEle) {

				case 0:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getQuestionName());
					break;

				case 1:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(0).getAnswerOptionEnglish());
					break;

				case 2:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(1).getAnswerOptionEnglish());
					break;
				case 3:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(2).getAnswerOptionEnglish());
					break;
				case 4:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(3).getAnswerOptionEnglish());
					break;
				case 5:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getQuestionHindi());
					break;
				case 6:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(0).getAnswerOptionHindi());
					break;
				case 7:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(1).getAnswerOptionHindi());
					break;
				case 8:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(2).getAnswerOptionHindi());
					break;
				case 9:
					cell.setCellType(CellType.STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(3).getAnswerOptionHindi());
					break;
				case 10:
					cell.setCellType(CellType.STRING);
					// cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getQuestionId());
					break;
				case 11:
					cell.setCellType(CellType.STRING);
					// cell.setCellStyle(cellStyle);
					cell.setCellValue(entity.getOptions().get(3).getOptionOrder());
					break;

				}

			}
			rowIndex++;
		}
		OutputStream bos = new FileOutputStream(new File("C:\\Users\\DELL\\Desktop\\questions.xlsx"));
		try {
			workbook.write(bos);
			
		} catch (Exception e) {
		} finally {
			bos.close();
			workbook.close();
		}

	}
	
	public void deleteQuestions(int i) 
	{
		repo.deleteByExamId(i);
	}

	public void deleteQuestionImages(int i) 
	{
		repo.deleteImageByExamId1(i);
		repo.deleteImageByExamId2(i);
	}

	public void findAllEncQuestions() throws Exception 
	{
            List<Question>  list = repo.findAll();		
            List<Question>  list2 = new ArrayList<Question>();
            for(Question q : list)
            {
            	q.getQuestionName();
            	q.getQuestionHindi();
            	list2.add(q);
            	
            }
            
            repo.saveAll(list2);
	}

	public List<DescriptiveQuestion> findBySetNoAndExamId2(String setNo, Integer examId) 
	{
		return desrepo.findCountBySetNoAndExamId(setNo, examId);
	}

	public List<DescriptiveQuestion> findAll2() 
	{
		return desrepo.findAll();
	}

	public void deleteDesQuestionImages(int examId) 
	{
		desrepo.deleteImageByExamId1(examId);
		desrepo.deleteImageByExamId2(examId);
	}

	public void deleteDesQuestions(int examId)
	{
		desrepo.deleteByExamId(examId);
	}

	
	public ExamQuestions savAll(ExamQuestions questions)
	{
		return examQuestionRepository.save(questions);
	}

	public ExamQuestions findExamQuestionsByExamid(int exam_id) {
		return examQuestionRepository.findExamQuestionsByExamid(exam_id);
	}

	public List<DescriptiveQuestion> findDesQuestions(int exam_id)
	{
	   return desrepo.findByExam(exam_id);
	}

	
}
