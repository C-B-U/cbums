package com.cbums.api;

import com.cbums.core.form.dto.FormResponse;
import com.cbums.core.form.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @GetMapping("")
    public ResponseEntity<List<FormResponse>> getFormList() {
        return ResponseEntity.ok(formService.findAll());
    }

    //기존 회원 & 신규 회원 나누어야 TODO
    @GetMapping("/{formId}")
    public ResponseEntity<FormResponse> getForm(Model model,
                                                @PathVariable("formId") Long formId) {

        FormResponse formResponse = formService.findForm(formId);

        //중복으로 넣어도 되려나...? TODO
        model.addAttribute("form", formId);
        return ResponseEntity.ok(formResponse);
    }

}

