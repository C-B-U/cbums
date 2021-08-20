package com.cbums.core.answer.service;

import com.cbums.core.answer.domain.Answer;
import com.cbums.core.answer.dto.AnswerRequest;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.service.FormService;
import com.cbums.core.member.domain.Member;
import com.cbums.core.answer.domain.AnswerRepository;
import com.cbums.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository formAnswerRepository;

    private final FormService formService;
    private final MemberService memberService;


    public Long createAnswer(List<AnswerRequest> answerRequests, String email){
        Member member = memberService.findByEmail(email);
        for(AnswerRequest ar : answerRequests) {
            formService.find
            ar.getQuestionId();
            Answer answer = new Answer().builder()
                    .question()
                    .content(ar.getContent())
                    .build();
        }
        Answer answer = Answer.builder()

    }
    public List<Long> createAnswer() {



        FormQuestion formItem;
        Answer formAnswer;
        for(Long k : contentKeyList) {
            formItem = formContentService.findFormContentById(k);
            formAnswer = Answer.builder()
                    .member(member)
                    .formItem(formItem)
                    .content(answer.get(k))
                    .build();
            formAnswerIdList.add(formAnswerRepository.save(formAnswer).getFormAnswerId());
        }
        return formAnswerIdList;
    }

    public List<Answer> findFormAnswerListByFormId(Long formId) {
        List<Answer> answerList = formAnswerRepository.findFormAnswerListByFormId(formId);
        if(answerList.isEmpty()) throw new NoSuchElementException();
        return answerList;
    }

    public List<Answer> findFormAnswerByFormIdAndMemberId(Long formId, Long memberId) {
        List<Answer> answerList = formAnswerRepository.findFormAnswerByFormIdAndMemberId(formId, memberId);
        if(answerList.isEmpty()) throw new NoSuchElementException();
        return answerList;
    }
}
