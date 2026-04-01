package com.ooj.langchain4jconsultant.tools;

import com.ooj.langchain4jconsultant.pojo.Reservation;
import com.ooj.langchain4jconsultant.service.ReservationService;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationTool {
    @Autowired
    private ReservationService reservationService;

    //添加预约信息
    @Tool("预约志愿填报服务")
    public void addReservation(
            @P("考生姓名") String name,
            @P("考生性别") String gender,
            @P("考生手机号") String phone,
            @P("预约时间，格式为yyyy-mm-dd'T'dd-mm") String communicationTime,
            @P("考生所在省份") String province,
            @P("考生预估分数") Integer estimatedScore
    ){
        reservationService.saveReservation(new Reservation(null, name, gender, phone, LocalDateTime.parse(communicationTime), province, estimatedScore));
    }

    //根据手机号查询预约信息
    @Tool("根据手机号查询预约信息")
    public Reservation queryReservation(
            @P("考生预约的手机号")String phone
    ){
        return reservationService.getReservationByPhone(phone);
    }



}
