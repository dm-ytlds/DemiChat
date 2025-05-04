package com.demi.java.ai.langchain4j.controller;

import com.demi.java.ai.langchain4j.assistant.MedicalAgent;
import com.demi.java.ai.langchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Tag(name = "智慧医疗")
@Validated  // 新增类级校验注解
@RestController
@RequestMapping("/medical")
public class MedicalController {

    @Autowired
    private MedicalAgent medicalAgent;

    @Operation(summary = "智能对话")
    @PostMapping(value = "/chat", produces = "text/event-stream;charset-utf-8")
    /*public String chat(@RequestBody ChatForm form) {
        return medicalAgent.chat(form.getMemoryId(), form.getMessage());
    }*/
    public Flux<String> chat(@RequestBody ChatForm form) {
        return medicalAgent.chat(form.getMemoryId(), form.getMessage());
    }
}
