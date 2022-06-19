package com.ttipl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ttipl.pojo.OtpVerification;

public interface OtpVerificationRepo extends CrudRepository<OtpVerification, Integer> {

	@Query(" select o.otp from OtpVerification o where o.exam_loc_session_id=?1")
	String findIsOTPIsOrNot(int exam_loc_session_id);

	@Query(value = "select els.location_name ,els.start_time,els.end_time,els.contact_name,ov.otp,ov.generated_time, els.exam_loc_session_id from otp_verification ov inner join exam_loc_session els on ov.exam_loc_session_id =els.exam_loc_session_id where els.exam_id like ?1", nativeQuery = true)
	List<Object[]> findOTPByExamId(String exam_id);
}
