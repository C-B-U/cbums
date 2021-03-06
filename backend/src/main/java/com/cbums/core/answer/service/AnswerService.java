package com.cbums.core.answer.service;

import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.answer.domain.Answer;
import com.cbums.core.answer.domain.AnswerRepository;
import com.cbums.core.answer.dto.AnswerRequest;
import com.cbums.core.answer.dto.AnswerResponse;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.service.FormService;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    private final FormService formService;
    private final MemberService memberService;


    public void createAnswer(SessionUser user, List<AnswerRequest> answerRequests){
        Member member = memberService.findByName(user.getName());
        for(AnswerRequest ar : answerRequests) {
            Question question = formService.findQuestionById(ar.getQuestionId());
            ar.getQuestionId();
            Answer answer = new Answer().builder()
                    .question(question)
                    .content(ar.getContent())
                    .member(member)
                    .build();
            answerRepository.save(answer);
        }
    }

    public List<AnswerResponse> findAnswersByFormId(Long formId) {
        List<Answer> answerList = answerRepository.findAnswersByFormId(formId);
        return AnswerResponse.listOf(answerList);
    }

    public List<AnswerResponse> findFormAnswerByFormIdAndMemberId(Long memberId, Long formId) {
        List<Answer> answers = answerRepository.findAnswersByMemberClassNumberAndFormId(memberId, formId);
        return Collections.unmodifiableList(AnswerResponse.listOf(answers));
    }
}
