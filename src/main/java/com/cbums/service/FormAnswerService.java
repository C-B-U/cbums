package com.cbums.service;

import com.cbums.model.FormAnswer;
import com.cbums.model.FormContent;
import com.cbums.model.Member;
import com.cbums.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FormAnswerService {
    private final FormAnswerRepository formAnswerRepository;
    private final FormContentRepository formContentRepository;
    //MemberService를 받아야 하나 Repo를 받아야하나... TODO
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;

    public void createFormAnswer(Map<Long,String> answer) {
        HttpSession httpSession = request.getSession();
        Long memberId = (Long)httpSession.getAttribute("form-writer-id");
        Member member = memberRepository.getById(memberId);
        List<Long> contentKeyList = new ArrayList<>(answer.keySet());
        FormContent formContent = null;
        FormAnswer formAnswer = null;
        for(Long k : contentKeyList) {
            formContent = formContentRepository.getById(k);
            formAnswer = new FormAnswer();
            formAnswer.setMember(member);
            formAnswer.setFormContent(formContent);
            formAnswer.setContent(answer.get(k));
            formAnswerRepository.save(formAnswer);
        }
    }

    public List<FormAnswer> findFormAnswerListByFormId(Long formId) {
        return formAnswerRepository.findFormAnswerListByFormId(formId);
    }

    public FormAnswer findFormAnswerByFormIdAndMemberId(Long formId, Long memberId) {

        return formAnswerRepository.findFormAnswerByFormIdAndMemberId(formId, memberId);
    }
}
