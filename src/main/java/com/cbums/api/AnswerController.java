package com.cbums.api;

import com.cbums.core.answer.dto.AnswerRequest;
import com.cbums.core.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping(value = "", produces = "application/json; charset=utf8")
    public ResponseEntity<Void> postFormAnswer(
            @Valid @RequestBody List<AnswerRequest> answerRequests
            , String email) {
        answerService.createAnswer(answerRequests, email);
        return ResponseEntity.created(URI.create("/form/submitted")).build();
    }
}
