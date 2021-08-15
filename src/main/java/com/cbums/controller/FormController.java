package com.cbums.controller;

import com.cbums.model.Form;
import com.cbums.model.FormContent;
import com.cbums.service.FormAnswerService;
import com.cbums.service.FormContentService;
import com.cbums.service.FormQuestionService;
import com.cbums.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;
    private final FormContentService formContentService;
    private final FormAnswerService formAnswerService;

    @GetMapping("")
    public List<Form> getFormList() throws NoSuchElementException {
        List<Form> formList = formService.findFormList();
        return formList;
    }

    //기존 회원 & 신규 회원 나누어야 TODO
    @GetMapping("/{seq}")
    public Form getForm(HttpServletResponse response,
                        @PathVariable("seq") Long seq) throws NullPointerException {

        Form form = formService.findFormById(seq);

        Cookie cookie = new Cookie("form-id", form.getFormId().toString());
        cookie.setMaxAge(3000);
        cookie.setPath("/");
        response.addCookie(cookie);
        return form;
    }

    @GetMapping("/content/{formSeq}")
    public List<FormContent> getFormContentQuestionList(@PathVariable("formSeq") Long formSeq) {

        List<FormContent> formContentList =
                formContentService.findFormContentListByFormId(formSeq);

        return formContentList;
    }

    //작성자 정보
    @PostMapping(value = "/content/answer", produces = "application/json; charset=utf8")
    public void postFormAnswer(HttpServletResponse response, @RequestBody Map<Long, String> answer) throws IOException {
        formAnswerService.createFormAnswer(answer);

        response.sendRedirect("/form/submitted");
    }


}

