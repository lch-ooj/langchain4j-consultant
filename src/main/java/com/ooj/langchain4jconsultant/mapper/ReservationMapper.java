package com.ooj.langchain4jconsultant.mapper;

import com.ooj.langchain4jconsultant.pojo.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReservationMapper {

    //插入预约信息
    @Insert("insert into reservation(name,gender,phone,communication_time,province,estimated_score) values(#{name},#{gender},#{phone},#{communicationTime},#{province},#{estimatedScore})")
    void insert(Reservation reservation);

    @Select("select * from reservation where phone=#{phone}")
    Reservation getByPhone(String phone);
}
