package com.cbums.controller.admin;

import com.cbums.controller.postParameter.CreateFormFormParameter;
import com.cbums.controller.postParameter.CreateFormQuestionParameter;
import com.cbums.model.Form;
import com.cbums.model.FormAnswer;
import com.cbums.model.FormQuestion;
import com.cbums.service.FormAnswerService;
import com.cbums.service.FormContentService;
import com.cbums.service.FormQuestionService;
import com.cbums.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/form")
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
    @PatchMapping("/{seq}")
    public String updateForm(@PathVariable("seq") Long seq, CreateFormFormParameter createFormFormParameter) {
        Form form = new Form();
        form.setIntroduce(createFormFormParameter.getIntroduce());
        form.setOpenDateTime(createFormFormParameter.getOpenDateTime());
        form.setCloseDateTime(createFormFormParameter.getCloseDateTime());
        form.setTitle(createFormFormParameter.getTitle());
        form.setRegisterNumber(createFormFormParameter.getRegisterNumber());
        form.setFormId(seq);
        formService.createForm(form);
        return "redirect:/form/" + seq;
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
    public  List<FormQuestion> getFormQuestionList() {

        List<FormQuestion> formQuestionList = formQuestionService.findFormQuestions();

        return formQuestionList;
    }

    @GetMapping("/question/{seq}")
    public FormQuestion getFormQuestion(@PathVariable("seq") Long seq) {

        FormQuestion formQuestion = formQuestionService.findFormQuestionById(seq);

        return formQuestion;
    }

    @PostMapping("/content/{formSeq}")
    public String setFormContent(@PathVariable("formSeq") Long formSeq, List<Long> FormQuestionId) {
        formContentService.createFormContent(formSeq, FormQuestionId);
        return "redirect:/form/content/"+formSeq;
    }

    @GetMapping("/content/{formSeq}/answer")
    public  List<FormAnswer> getFormAnswerList(@PathVariable("formSeq") Long formSeq) {

        List<FormAnswer> formAnswerList =
                formAnswerService.findFormAnswerListByFormId(formSeq);

        return formAnswerList;
    }

    @GetMapping("/content/{formSeq}/answer/{memberSeq}")
    public List<FormAnswer> getFormAnswer(
            @PathVariable("formSeq") Long formSeq,
            @PathVariable("memberSeq") Long memberSeq) {

        List<FormAnswer> formAnswerList =
                formAnswerService.findFormAnswerByFormIdAndMemberId(formSeq, memberSeq);

        return formAnswerList;
    }


}
