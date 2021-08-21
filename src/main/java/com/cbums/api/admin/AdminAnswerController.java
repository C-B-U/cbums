package com.cbums.api.admin;

import com.cbums.core.answer.domain.Answer;
import com.cbums.core.answer.dto.AnswerResponse;
import com.cbums.core.answer.dto.AnswerResponseByMember;
import com.cbums.core.answer.service.AnswerService;
import com.cbums.core.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/answer")
@RequiredArgsConstructor
public class AdminAnswerController {

    private final AnswerService answerService;

    @GetMapping("/")
    public ResponseEntity<AnswerResponseByMember> getFormAnswerList(@RequestParam("form-id") Long formId) {
        return ResponseEntity.ok(answerService.findAnswersByFormId(formId));
    }

    @GetMapping("/")
    public ResponseEntity<List<AnswerResponse>> getFormAnswer(@RequestParam("form-id") Long formId,
                                                              @PathVariable("member-class-num") String memberClassNum) {
        return ResponseEntity.ok(answerService.findFormAnswerByFormIdAndMemberId(memberClassNum, formId));
    }
}
