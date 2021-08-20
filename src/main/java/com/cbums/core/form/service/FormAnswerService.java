package com.cbums.core.form.service;

import com.cbums.core.form.domain.Answer;
import com.cbums.core.member.domain.Member;
import com.cbums.core.form.domain.AnswerRepository;
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
public class FormAnswerService {
    private final AnswerRepository formAnswerRepository;

    private final FormContentService formContentService;
    private final MemberService memberService;
    private final HttpServletRequest request;

    public List<Long> createFormAnswer(Map<Long,String> answer) {
        List<Long> formAnswerIdList = new ArrayList<>();
        HttpSession httpSession = request.getSession();
        Long memberId;
        if(httpSession.getAttribute("login-user") != null) {
            memberId = (Long)httpSession.getAttribute("login-user");
        }else {
            memberId = (Long)httpSession.getAttribute("form-writer-id");
            httpSession.removeAttribute("form-writer-id");
        }
        Member member = memberService.findMemberById(memberId);
        List<Long> contentKeyList = new ArrayList<>(answer.keySet());
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
