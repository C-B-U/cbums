package com.cbums.controller;

import com.cbums.model.Form;
import com.cbums.model.FormContent;
import com.cbums.model.FormQuestion;
import com.cbums.service.FormAnswerService;
import com.cbums.service.FormContentService;
import com.cbums.service.FormQuestionService;
import com.cbums.service.FormService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;
    private final FormQuestionService formQuestionService;
    private final FormContentService formContentService;
    private final FormAnswerService formAnswerService;

    @GetMapping("")
    public JsonObject getFormList() {
        JsonObject jsonObject = new JsonObject();
        List<Form> formList = formService.findForms();
        JsonArray jsonArr = new Gson().toJsonTree(formList).getAsJsonArray();
        jsonObject.add("formList", jsonArr);
        return jsonObject;
    }

    @GetMapping("/{seq}")
    public JsonObject getForm(@PathVariable("seq") Long seq) {
        JsonObject jsonObject = new JsonObject();
        Form form = formService.findFormById(seq);
        JsonParser jsonParser = new JsonParser();
        jsonObject.add("form", jsonParser.parse(form.toString()).getAsJsonObject());
        return jsonObject;
    }

    @GetMapping("/{seq}/question")
    public JsonObject getFormContentQuestionList(@PathVariable("seq") Long seq) {
        JsonObject jsonObject = new JsonObject();
        List<FormQuestion> formQuestionList =
                formContentService.findFormContentQuestionListByFormId(seq);
        JsonArray jsonArr = new Gson().
                toJsonTree(formQuestionList).getAsJsonArray();
        jsonObject.add("formQuestionList", jsonArr);

        return jsonObject;
    }

    //이것에 과연 필요한 코드인가?
    @GetMapping("/{formSeq}/question/{questionSeq}")
    public JsonObject getFormContentQuestion(
            @PathVariable("formSeq") Long formSeq,
            @PathVariable("questionSeq") Long questionSeq) {
        JsonObject jsonObject = new JsonObject();
        JsonParser jsonParser = new JsonParser();
        Form form = formService.findFormById(formSeq);
        FormQuestion formQuestion = formQuestionService.findFormQuestionById(questionSeq);

        jsonObject.add("form", jsonParser.parse(form.toString()).getAsJsonObject());
        jsonObject.add("formQuestion", jsonParser.parse(formQuestion.toString()).getAsJsonObject());

        return jsonObject;
    }

    //작성자 정보
    @PostMapping(value = "/{formSeq}/answer}",  produces = "application/json; charset=utf8")
    public String postFormAnswer(@PathVariable("formSeq") Long formSeq,
                @RequestBody Map<Long, String> answer) {
        formAnswerService.createFormAnswer(formSeq, answer);
        return "redirect:/";
    }


}

