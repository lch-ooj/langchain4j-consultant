package com.ooj.langchain4jconsultant;

import com.ooj.langchain4jconsultant.pojo.Reservation;
import com.ooj.langchain4jconsultant.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class ReservationServiceTest {
	@Autowired
	private ReservationService reservationService;

	@Test
	void testInsert() {
		Reservation reservation = new Reservation();
		reservation.setName("张三");
		reservation.setGender("男");
		reservation.setPhone("13800000000");
		reservation.setCommunicationTime(LocalDateTime.now());
		reservation.setProvince("北京");
		reservation.setEstimatedScore(588);
		reservationService.saveReservation(reservation);
	}

	@Test
	void testGetByPhone() {
		Reservation reservation = reservationService.getReservationByPhone("13800000000");
		System.out.println(reservation);
	}

}
