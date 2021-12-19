package com.cbums.api.admin;

import com.cbums.core.answer.dto.AnswerResponse;
import com.cbums.core.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/answer")
@RequiredArgsConstructor
public class AdminAnswerController {

    private final AnswerService answerService;

    @GetMapping("/{form-id}")
    public ResponseEntity<List<AnswerResponse>> getFormAnswerList(@PathVariable("form-id") Long formId) {
        return ResponseEntity.ok(answerService.findAnswersByFormId(formId));
    }

    @GetMapping("/{form-id}/member/{member-id}")
    public ResponseEntity<List<AnswerResponse>> getFormAnswer(@PathVariable("form-id") Long formId,
                                                              @PathVariable("member-id") Long memberId) {
        return ResponseEntity.ok(answerService.findFormAnswerByFormIdAndMemberId(memberId, formId));
    }
}
