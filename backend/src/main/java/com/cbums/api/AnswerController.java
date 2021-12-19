package com.cbums.api;

import com.cbums.config.auth.LoginUser;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.answer.dto.AnswerRequest;
import com.cbums.core.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping(value = "/", produces = "application/json; charset=utf8")
    public ResponseEntity<Void> postAnswer(@LoginUser SessionUser user,
            @Valid @RequestBody List<AnswerRequest> answerRequests) {
        answerService.createAnswer(user, answerRequests);

        // 추후에 Front에서 원하는 URL로 수정 TODO
        return ResponseEntity.created(URI.create("/form/")).build();
    }
}
