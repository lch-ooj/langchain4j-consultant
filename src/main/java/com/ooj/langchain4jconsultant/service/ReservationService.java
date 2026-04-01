package com.ooj.langchain4jconsultant.service;

import com.ooj.langchain4jconsultant.pojo.Reservation;
import com.ooj.langchain4jconsultant.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;

    public void saveReservation(Reservation reservation){
        //保存预约信息
        reservationMapper.insert(reservation);
    }

    public Reservation getReservationByPhone(String phone){
        //根据手机号查询预约信息
        return reservationMapper.getByPhone(phone);
    }
}
