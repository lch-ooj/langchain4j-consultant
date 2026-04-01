package com.ooj.langchain4jconsultant.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB使用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_messages")//映射到mongodb的chat_messages集合
public class ChatMessages {

    //唯一标识，会话id，映射到chat_message文档的_id字段
    @Id
    private ObjectId messageId;

    private String memoryId;

    //存储当前聊天记录的json字符串（）
    private String content;

}
