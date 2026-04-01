package com.ooj.langchain4jconsultant.controller;

import com.ooj.langchain4jconsultant.aiservice.ConsultantService;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    @Autowired
    private ConsultantService consultantService;

    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(String memoryId, String message) {
        return consultantService.streamChat(memoryId, message);
    }




    /*@Autowired
    private OpenAiChatModel model;
    @RequestMapping("/")
    public String chat(String message) {
        return model.chat(message);
    }*/
}
