package com.ttipl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatapushDao {
	public Connection conn = null, local_conn = null;;
	public PreparedStatement pstmt = null, pstmt2 = null;
	public ResultSet rs = null;

	/*
	 * public String pushQuestionToServer(DriverManagerDataSource dataSourceLocal,
	 * DriverManagerDataSource dataSourceServer, String examName, String setNo) {
	 * 
	 * int counter = 0;
	 * 
	 * try { local_conn = dataSourceLocal.getConnection(); conn =
	 * dataSourceServer.getConnection();
	 * 
	 * String query2 = "SELECT * from storing_question where"; if (examName != null)
	 * { query2 = query2 + " and examName='" + examName + "'  "; } if (setNo !=
	 * null) { query2 = query2 + " and setNo='" + setNo + "'  "; }
	 * 
	 * if (conn != null && local_conn != null) {
	 * 
	 * pstmt = local_conn.prepareStatement(query2); rs = pstmt.executeQuery();
	 * 
	 * String query3 =
	 * "insert into storing_question(question_id,exam_name,option1,option2,option3,option4,question,set_no) values(?,?,?,?,?,?,?,?)"
	 * ; pstmt2 = conn.prepareStatement(query3);
	 * 
	 * counter = 0; while (rs.next()) {
	 * 
	 * counter++; pstmt2.setString(1, rs.getString("question_id"));
	 * pstmt2.setString(2, rs.getString("exam_name")); pstmt2.setString(3,
	 * rs.getString("option1")); pstmt2.setString(4, rs.getString("option2"));
	 * pstmt2.setString(5, rs.getString("option3")); pstmt2.setString(6,
	 * rs.getString("option4")); pstmt2.setString(7, rs.getString("question"));
	 * pstmt2.setString(8, rs.getString("set_no")); pstmt2.executeUpdate();
	 * 
	 * }
	 * 
	 * if (counter > 0) {
	 * 
	 * return "Data successfully Pushed - Records Count - " + counter; } else {
	 * return "No Data Available to Pushed"; }
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); return e.getMessage(); }
	 * finally { try { if (local_conn != null && !local_conn.isClosed())
	 * local_conn.close(); if (conn != null && !conn.isClosed()) conn.close(); }
	 * catch (Exception e) { e.printStackTrace(); } } return "Success"; }
	 */
	public String pushPostsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int postId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from post where ";
			if (postId != 0) {
				query2 = query2 + "post_id ='" + postId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into post(post_id,no_of_vacancies,post_description,post_name,post_qualifications,post_category_id) values(?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setString(1, rs.getString("post_id"));
					pstmt2.setString(2, rs.getString("no_of_vacancies"));
					pstmt2.setString(3, rs.getString("post_description"));
					pstmt2.setString(4, rs.getString("post_name"));
					pstmt2.setString(5, rs.getString("post_qualifications"));
					pstmt2.setString(6, rs.getString("post_category_id"));
					pstmt2.executeUpdate();

				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pushed";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public String pushExamsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from exam where ";
			if (examId != 0) {
				query2 = query2 + "id='" + examId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into exam(id,duration,exam_code,exam_date,exam_description,exam_name,marks_per_question,negativemarks_per_question,post_id) values(?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setString(1, rs.getString("id"));
					pstmt2.setString(2, rs.getString("duration"));
					pstmt2.setString(3, rs.getString("exam_code"));
					pstmt2.setString(4, rs.getString("exam_date"));
					pstmt2.setString(5, rs.getString("exam_description"));
					pstmt2.setString(6, rs.getString("exam_name"));
					pstmt2.setString(7, rs.getString("marks_per_question"));
					pstmt2.setString(8, rs.getString("negativemarks_per_question"));
					pstmt2.setString(9, rs.getString("post_id"));

					pstmt2.executeUpdate();

				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pushed";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public String pushExamLocationSessionsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from exam_loc_session where ";
			if (examId != 0) {
				query2 = query2 + "exam_id='" + examId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into exam_loc_session (exam_loc_session_id,contact_name,address,moblie_number,session_date_time,end_time,exam_id,ipaddress,location_name,start_time) values(?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setInt(1, rs.getInt("exam_loc_session_id"));
					pstmt2.setString(2, rs.getString("contact_name"));
					pstmt2.setString(3, rs.getString("address"));
					pstmt2.setString(4, rs.getString("moblie_number"));
					pstmt2.setString(5, rs.getString("session_date_time"));
					pstmt2.setString(6, rs.getString("end_time"));
					pstmt2.setInt(7, rs.getInt("exam_id"));
					pstmt2.setString(8, rs.getString("ipaddress"));
					pstmt2.setString(9, rs.getString("location_name"));
					pstmt2.setString(9, rs.getString("start_time"));

					pstmt2.executeUpdate();

				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pushed";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

	public String pushQuestionsToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId, int locationSessionId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from question where ";
			if (examId != 0) {
				query2 = query2 + "exam_id='" + examId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();
				String query3 = "insert into question (question_id,bank_question_id,exam_id,exam_loc_session_id,question,question_no,question_type,question_hindi,question_paper_code,status) values(?,?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setInt(1, rs.getInt("question_id"));
					pstmt2.setInt(2, rs.getInt("bank_question_id"));
					pstmt2.setInt(3, rs.getInt("exam_id"));
					pstmt2.setInt(4, locationSessionId);
					pstmt2.setString(5, rs.getString("question"));
					pstmt2.setInt(6, rs.getInt("question_no"));
					pstmt2.setString(7, rs.getString("question_type"));
					pstmt2.setString(8, rs.getString("question_hindi"));
					pstmt2.setString(9, rs.getString("question_paper_code"));
					pstmt2.setBoolean(10, rs.getBoolean("status"));
					pstmt2.executeUpdate();

				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pushed";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";
	}

	public String pushCandidatesToLocationServerDb(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, int examId, int locationSessionId) throws SQLException {
		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from candidate where ";
			if (examId != 0) {
				query2 = query2 + "exam_id='" + examId + "'";
			}
			if (locationSessionId != 0) {
				query2 = query2 + "exam_loc_session_id='" + locationSessionId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into candidate(candidate_id,address,candidate_first_name,candidate_last_name,caste,community,contact_no,district,dob,email_id,exam_code,exam_duration,exam_end_time,exam_id,exam_is_end,exam_loc_session_id,father_name,gender,lab_location_id,login_ip,password,photo,pincode,post_id,post_name,religion,state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);
				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setInt(1, rs.getInt("candidate_id"));
					pstmt2.setString(2, rs.getString("address"));
					pstmt2.setString(3, rs.getString("candidate_first_name"));
					pstmt2.setString(4, rs.getString("candidate_last_name"));
					pstmt2.setString(5, rs.getString("caste"));
					pstmt2.setString(6, rs.getString("community"));
					pstmt2.setString(7, rs.getString("contact_no"));
					pstmt2.setString(8, rs.getString("district"));
					pstmt2.setDate(9, rs.getDate("dob"));
					pstmt2.setString(10, rs.getString("email_id"));
					pstmt2.setString(11, rs.getString("exam_code"));
					pstmt2.setString(12, rs.getString("exam_duration"));
					pstmt2.setString(13, rs.getString("exam_end_time"));
					pstmt2.setInt(14, rs.getInt("exam_id"));
					pstmt2.setString(15, rs.getString("exam_is_end"));
					pstmt2.setInt(16, rs.getInt("exam_loc_session_id"));
					pstmt2.setString(17, rs.getString("father_name"));
					pstmt2.setString(18, rs.getString("gender"));
					pstmt2.setInt(19, rs.getInt("lab_location_id"));
					pstmt2.setString(20, rs.getString("login_ip"));
					pstmt2.setString(21, rs.getString("password"));
					pstmt2.setBlob(22, rs.getBlob("photo"));
					pstmt2.setString(23, rs.getString("pincode"));
					pstmt2.setInt(24, rs.getInt("post_id"));
					pstmt2.setString(25, rs.getString("post_name"));
					pstmt2.setString(26, rs.getString("religion"));
					pstmt2.setString(27, rs.getString("state"));

					pstmt2.executeUpdate();

				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pushed";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";
	}

	public String pushOptionsBasedOnQuestionIdToLocationServer(DriverManagerDataSource dataSourceLocal,
			DriverManagerDataSource dataSourceServer, Integer questionId) throws SQLException {

		int counter = 0;

		try {
			local_conn = dataSourceLocal.getConnection();
			conn = dataSourceServer.getConnection();

			String query2 = "SELECT * from answer_option where ";
			if (questionId != 0) {
				query2 = query2 + "question_id='" + questionId + "'";
			}

			if (conn != null && local_conn != null) {

				pstmt = local_conn.prepareStatement(query2);
				rs = pstmt.executeQuery();

				String query3 = "insert into answer_option(answer_option_id,is_answer,answer_option,answer_option_hindi,option_order,question_id) values(?,?,?,?,?,?)";
				pstmt2 = conn.prepareStatement(query3);

				counter = 0;
				while (rs.next()) {

					counter++;
					pstmt2.setInt(1, rs.getInt("answer_option_id"));
					pstmt2.setBoolean(2, rs.getBoolean("is_answer"));
					pstmt2.setString(3, rs.getString("answer_option"));
					pstmt2.setString(4, rs.getString("answer_option_hindi"));
					pstmt2.setInt(5, rs.getInt("option_order"));
					pstmt2.setInt(6, rs.getInt("question_id"));
					pstmt2.executeUpdate();

				}

				if (counter > 0) {

					return "Data successfully Pushed - Records Count - " + counter;
				} else {
					return "No Data Available to Pushed";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			try {
				if (local_conn != null && !local_conn.isClosed())
					local_conn.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Success";

	}

}
