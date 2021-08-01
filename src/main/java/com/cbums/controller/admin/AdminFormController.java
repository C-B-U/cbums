package com.cbums.controller.admin;

import com.cbums.controller.postParameter.CreateFormFormParameter;
import com.cbums.controller.postParameter.CreateFormQuestionParameter;
import com.cbums.controller.postParameter.CreateFormContentParameter;
import com.cbums.model.Form;
import com.cbums.model.FormAnswer;
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

@RestController
@RequestMapping("/admin/form/**")
@RequiredArgsConstructor
public class AdminFormController {

    private final FormService formService;
    private final FormQuestionService formQuestionService;
    private final FormContentService formContentService;
    private final FormAnswerService formAnswerService;

    @PostMapping("/")
    public String postForm(CreateFormFormParameter createFormFormParameter) {
        Form form = new Form();
        form.setIntroduce(createFormFormParameter.getIntroduce());
        form.setOpenDateTime(createFormFormParameter.getOpenDateTime());
        form.setCloseDateTime(createFormFormParameter.getCloseDateTime());
        form.setTitle(createFormFormParameter.getTitle());
        form.setRegisterNumber(createFormFormParameter.getRegisterNumber());
        Long saveFormId = formService.createForm(form);
        return "redirect:/form/" + saveFormId;
    }

    @PostMapping("/question")
    public String postFormQuestion(CreateFormQuestionParameter createFormQuestionParameter) {
        FormQuestion formQuestion = new FormQuestion();
        formQuestion.setContent(createFormQuestionParameter.getContent());
        formQuestion.setOpeningDatetime(createFormQuestionParameter.getOpeningDatetime());
        Long saveFormQuestionId = formQuestionService.createFormQuestion(formQuestion);
        return "redirect:/admin/form/question/" + saveFormQuestionId;
    }

    @GetMapping("/question")
    public JsonObject getFormQuestionList() {
        JsonObject jsonObject = new JsonObject();
        List<FormQuestion> formQuestionList = formQuestionService.findFormQuestions();
        JsonArray jsonArr = new Gson().toJsonTree(formQuestionList).getAsJsonArray();
        jsonObject.add("formQuestionList", jsonArr);

        return jsonObject;
    }

    @GetMapping("/question/{seq}")
    public JsonObject getFormQuestion(@PathVariable("seq") Long seq) {
        JsonObject jsonObject = new JsonObject();
        FormQuestion formQuestion = formQuestionService.findFormQuestionById(seq);
        JsonParser jsonParser = new JsonParser();

        jsonObject.add("formQuestion", jsonParser.parse(formQuestion.toString()).getAsJsonObject());
        return jsonObject;
    }

    @PostMapping("/content")
    public String setFormContent(CreateFormContentParameter createFormContentParameter) {
        Long savedFormContentId = formContentService.createFormContent(
                createFormContentParameter.getFormId(),
                createFormContentParameter.getFormQuestionId());

        return "redirect:/form/content/"+savedFormContentId;
    }

    @GetMapping("/content/{formSeq}/answer")
    public JsonObject getFormAnswerList(@PathVariable("formSeq") Long formSeq) {
        JsonObject jsonObject = new JsonObject();
        List<FormAnswer> formAnswerList =
                formAnswerService.findFormAnswerListByFormId(formSeq);
        JsonArray jsonArr = new Gson().toJsonTree(formAnswerList).getAsJsonArray();
        jsonObject.add("formAnswerService", jsonArr);

        return jsonObject;
    }
    // 각 개인별로 조회 TODO
}
