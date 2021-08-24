package com.cbums.api;

import com.cbums.config.auth.LoginUser;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.answer.dto.AnswerRequest;
import com.cbums.core.answer.service.AnswerService;
import com.cbums.core.form.dto.QuestionResponse;
import com.cbums.core.form.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final FormService formService;

    @GetMapping("/{formId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionList(@PathVariable("formId") Long formId) {
        return ResponseEntity.ok(formService.findQuestionsByFormId(formId));
    }

    @PostMapping(value = "/", produces = "application/json; charset=utf8")
    public ResponseEntity<Void> postFormAnswer(@LoginUser SessionUser user,
            @Valid @RequestBody List<AnswerRequest> answerRequests) {
        answerService.createAnswer(user, answerRequests);
        return ResponseEntity.created(URI.create("/form/submitted")).build();
    }
}
