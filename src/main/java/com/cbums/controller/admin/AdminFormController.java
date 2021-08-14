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
import com.cbums.service.exception.NotLoginedException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin/form")
@RequiredArgsConstructor
public class AdminFormController {

    private final FormService formService;
    private final FormQuestionService formQuestionService;
    private final FormContentService formContentService;
    private final FormAnswerService formAnswerService;

    @GetMapping("")
    public List<Form> getFormList() throws NoSuchElementException {
        List<Form> formList = formService.findFormList();
        return formList;
    }

    @PostMapping("/")
    public void createForm(HttpServletResponse response,
                           @RequestBody CreateFormFormParameter createFormFormParameter) throws NotLoginedException, IOException {
        Form form = new Form();
        form.setIntroduce(createFormFormParameter.getIntroduce());
        form.setOpenDateTime(createFormFormParameter.getOpenDateTime());
        form.setCloseDateTime(createFormFormParameter.getCloseDateTime());
        form.setTitle(createFormFormParameter.getTitle());
        form.setRegisterNumber(createFormFormParameter.getRegisterNumber());
        Long saveFormId = formService.createForm(form);
        response.sendRedirect("/admin/form/" + saveFormId);
    }

    @GetMapping("/{seq}")
    public Form getForm(@PathVariable("seq") Long seq) throws NullPointerException {

        Form form = formService.findFormById(seq);

        return form;
    }

    @PatchMapping("/{seq}")
    public void updateForm(HttpServletResponse response,
                           @PathVariable("seq") Long seq,
                           @RequestBody CreateFormFormParameter createFormFormParameter) throws NotLoginedException, IOException {
        Form form = new Form();
        form.setIntroduce(createFormFormParameter.getIntroduce());
        form.setOpenDateTime(createFormFormParameter.getOpenDateTime());
        form.setCloseDateTime(createFormFormParameter.getCloseDateTime());
        form.setTitle(createFormFormParameter.getTitle());
        form.setRegisterNumber(createFormFormParameter.getRegisterNumber());
        form.setFormId(seq);
        formService.createForm(form);
        response.sendRedirect("/admin/form/" + seq);
    }

    @DeleteMapping("/{seq}")
    public void deleteForm(HttpServletResponse response, @PathVariable("seq") Long seq)
            throws NotLoginedException, IOException {
        formService.deleteForm(seq);

        response.sendRedirect("/admin/form");
    }

    @GetMapping("/question")
    public List<FormQuestion> getFormQuestionList() {

        List<FormQuestion> formQuestionList = formQuestionService.findFormQuestions();

        return formQuestionList;
    }

    @PostMapping("/question")
    public void postFormQuestion(HttpServletResponse response,
                                 CreateFormQuestionParameter createFormQuestionParameter) throws NotLoginedException, IOException {
        FormQuestion formQuestion = new FormQuestion();
        formQuestion.setContent(createFormQuestionParameter.getContent());
        formQuestion.setOpeningDatetime(createFormQuestionParameter.getOpeningDatetime());
        Long saveFormQuestionId = formQuestionService.createFormQuestion(formQuestion);
        response.sendRedirect("redirect:/admin/form/question/" + saveFormQuestionId);
    }

    //필요할까?
    @GetMapping("/question/{seq}")
    public FormQuestion getFormQuestion(@PathVariable("seq") Long seq) {

        FormQuestion formQuestion = formQuestionService.findFormQuestionById(seq);

        return formQuestion;
    }

    @PostMapping("/content/{formSeq}")
    public void setFormContent(HttpServletResponse response,
                               @PathVariable("formSeq") Long formSeq,
                               List<Long> FormQuestionId) throws IOException {
        formContentService.createFormContent(formSeq, FormQuestionId);
        response.sendRedirect("redirect:/form/content/" + formSeq);
    }

    @GetMapping("/content/{formSeq}/answer")
    public List<FormAnswer> getFormAnswerList(@PathVariable("formSeq") Long formSeq) {

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
