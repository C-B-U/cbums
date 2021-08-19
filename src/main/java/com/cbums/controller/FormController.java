package com.cbums.controller;

import com.cbums.core.form.domain.Form;
import com.cbums.core.form.service.FormAnswerService;
import com.cbums.core.form.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;
    private final FormContentService formContentService;
    private final FormAnswerService formAnswerService;

    @GetMapping("")
    public ResponseEntity<List<Form>> getFormList() {
        return ResponseEntity.ok(formService.findFormList());
    }

    //기존 회원 & 신규 회원 나누어야 TODO
    @GetMapping("/{formId}")
    public ResponseEntity<Form> getForm(HttpServletResponse response,
                                        @PathVariable("formId") Long formId) {

        Form form = formService.findFormById(formId);

        Cookie cookie = new Cookie("form-id", form.getFormId().toString());
        cookie.setMaxAge(3000);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(form);
    }

    @GetMapping("/content/{formId}")
    public ResponseEntity<List<FormQuestion>> getFormContentQuestionList(@PathVariable("formId") Long formId) {

        return ResponseEntity.ok(formContentService.findFormContentListByFormId(formId));
    }

    //작성자 정보
    @PostMapping(value = "/content/answer", produces = "application/json; charset=utf8")
    public ResponseEntity<Void> postFormAnswer(@RequestBody Map<Long, String> answer) {
        formAnswerService.createFormAnswer(answer);
        return ResponseEntity.created(URI.create("/form/submitted")).build();
    }


}

