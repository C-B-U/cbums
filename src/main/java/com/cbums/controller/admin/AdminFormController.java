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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/form")
@RequiredArgsConstructor
public class AdminFormController {

    private final FormService formService;
    private final FormQuestionService formQuestionService;
    private final FormContentService formContentService;
    private final FormAnswerService formAnswerService;

    @GetMapping("")
    public ResponseEntity<List<Form>> getFormList() {
        return ResponseEntity.ok(formService.findFormList());
    }

    @PostMapping("/")
    public ResponseEntity<Void> createForm(@RequestBody CreateFormFormParameter createFormFormParameter) throws NotLoginedException {
        Form form = Form.builder()
                .introduce(createFormFormParameter.getIntroduce())
                .openDateTime(createFormFormParameter.getOpenDateTime())
                .closeDateTime(createFormFormParameter.getCloseDateTime())
                .title(createFormFormParameter.getTitle())
                .registerNumber(createFormFormParameter.getRegisterNumber())
                .build();

        Long saveFormId = formService.createForm(form);
        return ResponseEntity.created(URI.create("/admin/form/" + saveFormId)).build();

    }

    @GetMapping("/{seq}")
    public ResponseEntity<Form> getForm(@PathVariable("seq") Long seq) {
        return ResponseEntity.ok(formService.findFormById(seq));
    }

    @PatchMapping("/{seq}")
    public ResponseEntity<Void> updateForm(@PathVariable("seq") Long seq,
                                           @RequestBody CreateFormFormParameter createFormFormParameter) throws NotLoginedException {

        Form form = Form.builder()
                .formId(seq)
                .introduce(createFormFormParameter.getIntroduce())
                .openDateTime(createFormFormParameter.getOpenDateTime())
                .closeDateTime(createFormFormParameter.getCloseDateTime())
                .title(createFormFormParameter.getTitle())
                .registerNumber(createFormFormParameter.getRegisterNumber())
                .build();

        formService.createForm(form);

        return ResponseEntity.created(URI.create("/admin/form/" + seq)).build();
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteForm(@PathVariable("seq") Long seq) throws NotLoginedException {

        formService.deleteForm(seq);

        return ResponseEntity.created(URI.create("/admin/form")).build();

    }

    @GetMapping("/question")
    public ResponseEntity<List<FormQuestion>> getFormQuestionList() {
        return ResponseEntity.ok(formQuestionService.findFormQuestions());
    }

    @PostMapping("/question")
    public void postFormQuestion(HttpServletResponse response,
                                 CreateFormQuestionParameter createFormQuestionParameter) throws NotLoginedException, IOException {
        FormQuestion formQuestion = FormQuestion.builder()
                .content(createFormQuestionParameter.getContent())
                .openingDatetime(createFormQuestionParameter.getOpeningDatetime())
                .build();

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
    public ResponseEntity<Void> setFormContent(@PathVariable("formSeq") Long formSeq,
                                               List<Long> FormQuestionId) {

        formContentService.createFormContent(formSeq, FormQuestionId);

        return ResponseEntity.created(URI.create("/form/content/" + formSeq)).build();
    }

    @GetMapping("/content/{formSeq}/answer")
    public ResponseEntity<List<FormAnswer>> getFormAnswerList(@PathVariable("formSeq") Long formSeq) {
        return ResponseEntity.ok(formAnswerService.findFormAnswerListByFormId(formSeq));
    }

    @GetMapping("/content/{formSeq}/answer/{memberSeq}")
    public ResponseEntity<List<FormAnswer>> getFormAnswer(
            @PathVariable("formSeq") Long formSeq,
            @PathVariable("memberSeq") Long memberSeq) {
        return ResponseEntity.ok(formAnswerService.findFormAnswerByFormIdAndMemberId(formSeq, memberSeq));
    }


}
