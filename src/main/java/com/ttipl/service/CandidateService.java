package com.ttipl.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ttipl.pojo.Candidate;
import com.ttipl.repository.CandidateRepository;

@Service
@Transactional
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	ApplicationContext context;
	
	@Autowired
private	ExcelService service;

	public Candidate getData(Candidate candidate) {
		return candidateRepository.save(candidate);

	}

	public List<Candidate> findByExamSessionId(int id) {
		return candidateRepository.findBylocationName(id);
	}
	
	public List<Object[]> findByExamSessionId1(int id) {
		return candidateRepository.findBylocationName1(id);
	}

	public int deleteByExamSessionId(int id) {
		return candidateRepository.deleteCandRecordsByExamLoc_session(id);
	}

	public Candidate findByCandidateId(String id) {
		return candidateRepository.findBycandidateId(id);
	}

	public void saveCandidate(Candidate candidate) throws SQLException {
		candidateRepository.save(candidate);
	}
	
	@Transactional
	public void saveCandidates(List<Candidate> candidates) throws SQLException {
		candidateRepository.saveAll(candidates);
	}

	public List<Object[]> getAll() {
		return candidateRepository.getSomething();
	}

	public Candidate getById(String id) {
		return candidateRepository.findById(id);
	}

	public int deleteByOne(String id) {
		return candidateRepository.deleteCandRecord(id);
	}

	public int findDistinctByCandidateId() {
		return candidateRepository.findDistinctByCandidateId();
	}

	public int findDistinctByCandidateId(String startTime,String endTime, String examId) {
		return candidateRepository.findDistinctByCandidateId(startTime,endTime, examId);
	}

	public List<Object[]> getCandidateByExam_idAndExamSession_Id(int exam_id, int locationId) {
		return candidateRepository.getCandidateByExam_idAndExamSession_Id(exam_id, locationId);
	}

	public String importCodexlsx(MultipartFile filePart, int exam_id, int location_session_id, int no_of_cols,String excelPassword)
			throws IOException {

		List<Candidate> candidates = new ArrayList<Candidate>();
		InputStream inputStream = filePart.getInputStream();
		XSSFWorkbook wb=null;
		if (inputStream != null) {
			if (excelPassword != null && !excelPassword.equalsIgnoreCase("")) {
				try {
					inputStream =service.verifyExcelPassword(inputStream, excelPassword);
					if (inputStream != null) {
						return " <span class=\"font-red\">Invalid Password</span>";
					}
				} catch (GeneralSecurityException e) {
					return " <span class=\"font-red\">Internal server error</span>";
				} catch (OfficeXmlFileException e) {
					return " <span class=\"font-red\">Please check excel password protected or not</span>";
				}
			}
			try {
				wb = new XSSFWorkbook(inputStream);
			} catch (OfficeXmlFileException e) {
				return " <span class=\"font-red\">Please check excel password protected or not</span>";
			}

			XSSFSheet sheet = wb.getSheetAt(0);

			int noOfCols = 0;
			Cell cell = null;
			String CandidateId = "";
			String password = "";
			String dob = "";
			String mobileNO = "";
			String firstName = "";
			String lastName = "";
			String gender = "";
			String fatherName = "";
			String community = "";
			String email_id = "";
			int rowCount = 0;

			for (Row row : sheet) {
				noOfCols = row.getPhysicalNumberOfCells();
				if (noOfCols == no_of_cols) {
					if (rowCount >= 1) {
						CandidateId = "";
						dob = "";
						mobileNO = "";
						firstName = "";
						lastName = "";
						gender = "";
						fatherName = "";
						community = "";
						email_id = "";
						password = "";

						for (int i = 0; i < noOfCols; i++) {
							cell = row.getCell(i);
							if (i == 0) {
								if (cell.getCellType() == CellType.STRING) {
									CandidateId = cell.getStringCellValue();
									CandidateId = CandidateId.replaceAll("'", "");
									CandidateId = CandidateId.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									CandidateId = (long) cell.getNumericCellValue() + "";

								}
							}
							if (i == 1) {
								if (cell.getCellType() == CellType.STRING) {
									dob = cell.getStringCellValue();
									dob = dob.replaceAll("'", "");
									dob = dob.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									dob = cell.getNumericCellValue() + "";
									if (DateUtil.isCellDateFormatted(cell)) {
										SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
										dob = dateFormat.format(cell.getDateCellValue());
									} else {
										System.out.print(cell.getNumericCellValue() + "\t\t");
									}

								}
							}

							if (i == 2) {
								if (cell.getCellType() == CellType.STRING) {
									firstName = cell.getStringCellValue();
									firstName = firstName.replaceAll("'", "");
									firstName = firstName.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									firstName = cell.getNumericCellValue() + "";

								}
							}
							if (i == 3) {
								if (cell.getCellType() == CellType.STRING) {
									lastName = cell.getStringCellValue();
									lastName = lastName.replaceAll("'", "");
									lastName = lastName.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									lastName = cell.getNumericCellValue() + "";

								}
							}

							if (i == 4) {
								if (cell.getCellType() == CellType.STRING) {
									gender = cell.getStringCellValue();
									gender = gender.replaceAll("'", "");
									gender = gender.trim();
								} else if (cell.getCellType() == CellType.NUMERIC) {
									gender = cell.getNumericCellValue() + "";

								}
							}
							if (i == 5) {
								if (cell.getCellType() == CellType.STRING) {
									fatherName = cell.getStringCellValue();
									fatherName = fatherName.replaceAll("'", "");
									fatherName = fatherName.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									fatherName = cell.getNumericCellValue() + "";

								}
							}

							if (i == 6) {
								if (cell.getCellType() == CellType.STRING) {
									community = new DataFormatter().formatCellValue(cell);
									community = community.replaceAll("'", "");
									community = community.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									community = cell.getNumericCellValue() + "";
								}
							}

							if (i == 7) {
								if (cell.getCellType() == CellType.STRING) {
									mobileNO = new DataFormatter().formatCellValue(cell);

									mobileNO = mobileNO.replaceAll("'", "");
									mobileNO = mobileNO.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									mobileNO = new DataFormatter().formatCellValue(cell);
									// System.out.println(mobileNO+ "dddddd");
									// mobileNO = cell.getNumericCellValue() + "";

								}
							}

							if (i == 8) {
								if (cell.getCellType() == CellType.STRING) {
									email_id = cell.getStringCellValue();
									email_id = email_id.replaceAll("'", "");
									email_id = email_id.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									email_id = cell.getNumericCellValue() + "";

								}

							}
							if (i == 9) {
								if (cell.getCellType() == CellType.STRING) {
									password = cell.getStringCellValue();
									password = password.replaceAll("'", "");
									password = password.trim();

								} else if (cell.getCellType() == CellType.NUMERIC) {
									password = (int) cell.getNumericCellValue() + "";

								}

							}
							if (i == 10) {
								if (cell.getCellType() == CellType.STRING) {
									try {
										location_session_id = Integer.parseInt(cell.getStringCellValue().trim());
									} catch (NumberFormatException e) {
										e.printStackTrace();
									}

								} else if (cell.getCellType() == CellType.NUMERIC) {
									try {
										location_session_id = (int) cell.getNumericCellValue();
									} catch (Exception e) {

									}
								}
							}
						}

						if (rowCount >= 1) {
							Candidate cb = new Candidate();
							cb.setExam_id(exam_id);
							cb.setExam_loc_session_id(location_session_id);
							cb.setCandidate_first_name(firstName);
							cb.setCandidate_last_name(lastName);
							cb.setGender(gender);
							cb.setFather_name(fatherName);
							// cb.setCommunity(community);
							cb.setCaste(community);
							cb.setEmail_id(email_id);
							cb.setPassword(password);
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							try {
								cb.setDob(dateFormat.parse(dob));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							if (CandidateId != "") {
								cb.setCandidate_id(CandidateId);
							} else {
								wb.close();
								return "<span class=\"font-red\">candidate Id should not empty<span>";
							}

							try {
								cb.setContact_no(Long.parseLong(mobileNO));
							} catch (NumberFormatException e) {
							}
							candidates.add(cb);
						}

					}
					rowCount++;
				} else {
					wb.close();
					return "<span class=\"font-red\">Cell Count Not Matched. please refer the excel format<span>";
				}
			}
		
				try {
					saveCandidates(candidates);
				} catch (Exception e) {
					wb.close();
					return "<span class=\"font-red\">Kindly please check  the exam loc session Id is correct or not ! <span>";
				}

			
			wb.close();
			return "<span class=\"font-green\">Successfully ( " + candidates.size() + " ) candidates uploaded <span>";

		} else {
			return "Error While uploading";
		}

	}
}