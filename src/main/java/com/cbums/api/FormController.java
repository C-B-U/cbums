package com.cbums.api;

import com.cbums.core.form.dto.FormResponse;
import com.cbums.core.form.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @GetMapping("")
    public ResponseEntity<List<FormResponse>> getFormList() {
        return ResponseEntity.ok(formService.findAll());
    }

    @GetMapping("/{formId}")
    public ResponseEntity<FormResponse> getForm(@PathVariable("formId") Long formId) {

        FormResponse formResponse = formService.findForm(formId);
        return ResponseEntity.ok(formResponse);
    }

}

