package com.cbums.controller.admin;

import com.cbums.controller.postParameter.CreateFormFormParameter;
import com.cbums.controller.postParameter.CreateFormQuestionParameter;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.domain.Answer;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.service.FormAnswerService;
import com.cbums.core.form.service.FormQuestionService;
import com.cbums.core.form.service.FormService;
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
    public ResponseEntity<List<Question>> getFormQuestionList() {
        return ResponseEntity.ok(formQuestionService.findFormQuestions());
    }

    @PostMapping("/question")
    public void postFormQuestion(HttpServletResponse response,
                                 CreateFormQuestionParameter createFormQuestionParameter) throws NotLoginedException, IOException {
        Question question = Question.builder()
                .content(createFormQuestionParameter.getContent())
                .openingDatetime(createFormQuestionParameter.getOpeningDatetime())
                .build();

        Long saveFormQuestionId = formQuestionService.createFormQuestion(question);
        response.sendRedirect("redirect:/admin/form/question/" + saveFormQuestionId);
    }

    //필요할까?
    @GetMapping("/question/{seq}")
    public Question getFormQuestion(@PathVariable("seq") Long seq) {

        Question question = formQuestionService.findFormQuestionById(seq);

        return question;
    }

    @PostMapping("/content/{formSeq}")
    public ResponseEntity<Void> setFormContent(@PathVariable("formSeq") Long formSeq,
                                               List<Long> FormQuestionId) {

        formContentService.createFormContent(formSeq, FormQuestionId);

        return ResponseEntity.created(URI.create("/form/content/" + formSeq)).build();
    }

    @GetMapping("/content/{formSeq}/answer")
    public ResponseEntity<List<Answer>> getFormAnswerList(@PathVariable("formSeq") Long formSeq) {
        return ResponseEntity.ok(formAnswerService.findFormAnswerListByFormId(formSeq));
    }

    @GetMapping("/content/{formSeq}/answer/{memberSeq}")
    public ResponseEntity<List<Answer>> getFormAnswer(
            @PathVariable("formSeq") Long formSeq,
            @PathVariable("memberSeq") Long memberSeq) {
        return ResponseEntity.ok(formAnswerService.findFormAnswerByFormIdAndMemberId(formSeq, memberSeq));
    }


}
