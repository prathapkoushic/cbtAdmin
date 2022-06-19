package com.ttipl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.ttipl.pojo.CandidateQueAns;

@Component
public class PushResultService {

	Connection conn = null;
	PreparedStatement preparedStatement = null;

	public Connection getConnection() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		Properties connectionProperties = new Properties();
		connectionProperties.put("username", "bpssc_ttl");
		connectionProperties.put("password", "bpssc_ttil_1234");
		connectionProperties.put("url",
				"jdbc:mysql://bpsscrds.cof0fc3mnt2s.ap-southeast-1.rds.amazonaws.com:3306/onlinecbt2020");
		connectionProperties.put("driverClassName", "com.mysql.cj.jdbc.Driver");
		dataSource.setConnectionProperties(connectionProperties);
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	public String pushCandidateQaTable(List<CandidateQueAns> list) {
		conn = getConnection();
		if (conn != null) {

			String query = "insert into candidate_qa  (candidate_id,"
					+ "date_time,ipaddress,answer_option_id,question_id,"
					+ "question_no,remaining_time) values(?,?,?,?,?,?,?)";
			try {
				preparedStatement = conn.prepareStatement(query);
				for (CandidateQueAns qa : list) {
					preparedStatement.setString(1, qa.getCandidateId());
					preparedStatement.setObject(2, qa.getDateTime());
					preparedStatement.setString(3, qa.getIpAddress());
					preparedStatement.setInt(4, qa.getOptionId());
					preparedStatement.setInt(5, qa.getQuestionId());
					preparedStatement.setInt(6, qa.getQuestionNo());
					preparedStatement.setObject(7, qa.getRemainingTime());
					preparedStatement.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {

		}

		return "";

	}

}
