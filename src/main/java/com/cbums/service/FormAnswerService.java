package com.cbums.service;

import com.cbums.model.FormAnswer;
import com.cbums.model.FormContent;
import com.cbums.model.Member;
import com.cbums.repository.FormAnswerRepository;
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
    private final FormAnswerRepository formAnswerRepository;

    private final FormContentService formContentService;
    private final MemberService memberService;
    private final HttpServletRequest request;

    public List<Long> createFormAnswer(Map<Long,String> answer) {
        List<Long> formAnswerIdList = new ArrayList<>();
        HttpSession httpSession = request.getSession();
        Long memberId = (Long)httpSession.getAttribute("form-writer-id");
        httpSession.removeAttribute("form-writer-id");
        Member member = memberService.findMemberById(memberId);
        List<Long> contentKeyList = new ArrayList<>(answer.keySet());
        FormContent formContent;
        FormAnswer formAnswer;
        for(Long k : contentKeyList) {
            formContent = formContentService.findFormContentById(k);
            formAnswer = new FormAnswer();
            formAnswer.setMember(member);
            formAnswer.setFormContent(formContent);
            formAnswer.setContent(answer.get(k));
            formAnswerIdList.add(formAnswerRepository.save(formAnswer).getFormAnswerId());
        }
        return formAnswerIdList;
    }

    public List<FormAnswer> findFormAnswerListByFormId(Long formId) {
        List<FormAnswer> formAnswerList = formAnswerRepository.findFormAnswerListByFormId(formId);
        if(formAnswerList.isEmpty()) throw new NoSuchElementException();
        return formAnswerList;
    }

    public List<FormAnswer> findFormAnswerByFormIdAndMemberId(Long formId, Long memberId) {
        List<FormAnswer> formAnswerList = formAnswerRepository.findFormAnswerByFormIdAndMemberId(formId, memberId);
        if(formAnswerList.isEmpty()) throw new NoSuchElementException();
        return formAnswerList;
    }
}
