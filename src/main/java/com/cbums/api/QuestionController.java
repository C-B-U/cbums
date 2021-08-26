package com.cbums.api;


import com.cbums.core.form.dto.QuestionResponse;
import com.cbums.core.form.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final FormService formService;

    @GetMapping("/{formId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionList(@PathVariable("formId") Long formId) {
        return ResponseEntity.ok(formService.findQuestionsByFormId(formId));
    }
}
