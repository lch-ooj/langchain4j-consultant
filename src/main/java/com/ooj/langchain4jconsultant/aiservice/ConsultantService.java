package com.ooj.langchain4jconsultant.aiservice;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT, //手动装配 ，默认是自动
        chatModel = "openAiChatModel", //指定模型
        streamingChatModel = "openAiStreamingChatModel",
//        chatMemory = "chatMemory", //指定会话记忆对象
        chatMemoryProvider = "chatMemoryProvider",  //会话记忆提供者
        contentRetriever = "contentRetriever", //指定向量数据库检索对象
        tools = "reservationTool"
)
public interface ConsultantService {

    //声明用于聊天的方法
    String chat(String message);

/*    @UserMessage("你要说中文。{{msg}}")
    Flux<String> streamChat(@V("msg")String message);*/

    @SystemMessage(fromResource = "system.txt")
//    @UserMessage("你要说中文。{{it}}")
    Flux<String> streamChat(@MemoryId String memoryId,@UserMessage String message);
}
