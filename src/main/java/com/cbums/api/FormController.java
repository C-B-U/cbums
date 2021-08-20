package com.cbums.api;

import com.cbums.core.form.domain.Form;
import com.cbums.core.form.dto.FormResponse;
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
    private final FormAnswerService formAnswerService;

    @GetMapping("")
    public ResponseEntity<List<FormResponse>> getFormList() {
        return ResponseEntity.ok(formService.findAll());
    }

    //기존 회원 & 신규 회원 나누어야 TODO
    @GetMapping("/{formId}")
    public ResponseEntity<FormResponse> getForm(HttpServletResponse response,
                                        @PathVariable("formId") Long formId) {

        FormResponse formResponse = formService.findForm(formId);

        Cookie cookie = new Cookie("form-id", formId.toString());
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(formResponse);
    }

    //작성자 정보
    @PostMapping(value = "/content/answer", produces = "application/json; charset=utf8")
    public ResponseEntity<Void> postFormAnswer(@RequestBody Map<Long, String> answer) {
        formAnswerService.createFormAnswer(answer);
        return ResponseEntity.created(URI.create("/form/submitted")).build();
    }


}

