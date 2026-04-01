package com.ooj.langchain4jconsultant;

import com.ooj.langchain4jconsultant.pojo.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class mongoCRUDTest {
    @Autowired
    private MongoTemplate mongoTemplate;


   /* *//**
     * 插入文档
     *//*
    @Test
    void testInsert(){
        ChatMessages chatMessages=new ChatMessages(1L,"你好");
        mongoTemplate.insert(chatMessages);
    }*/


    /**
     * 插入文档
     */
    @Test
    void testInsert2(){
        ChatMessages chatMessages=new ChatMessages();
        chatMessages.setContent("聊天记录");
        mongoTemplate.insert(chatMessages);
    }

    /**
     * 根据id查询文档
     */
    @Test
    void testFindById(){
        ChatMessages chatMessages = mongoTemplate.findById("6934043bdbf43844470a0bec", ChatMessages.class);
        System.out.println(chatMessages);
    }

    /**
     * 根据id修改文档
     */
    @Test
    void testUpdateById(){
        Criteria criteria = Criteria.where("_id").is("6934043bdbf43844470a0bec");
        Query query = new Query(criteria);
        Update update = new Update();
        update.set("content","更新后的内容");
        //upsert可以修改或新增
        mongoTemplate.upsert(query, update, ChatMessages.class);
    }

    /**
     * 根据id删除文档
     */
    @Test
    void testDeleteById(){
        Criteria criteria = Criteria.where("_id").is("6934043bdbf43844470a0bec");
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }




}
