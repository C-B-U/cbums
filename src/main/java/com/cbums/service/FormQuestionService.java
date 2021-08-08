package com.cbums.service;

import com.cbums.model.FormQuestion;
import com.cbums.model.Member;
import com.cbums.repository.FormQuestionRepository;
import com.cbums.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormQuestionService {
    private final FormQuestionRepository formQuestionRepository;
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;

    public Long createFormQuestion(FormQuestion formQuestion) {
        HttpSession httpSession = request.getSession();
        Long producerId = (Long)httpSession.getAttribute("login-user");
        Member producer = memberRepository.getById(producerId);
        formQuestion.setProducer(producer);
        FormQuestion savedFormQuestion = formQuestionRepository.save(formQuestion);

        return savedFormQuestion.getFormQuestionId();
    }

    public List<FormQuestion> findFormQuestions() { return formQuestionRepository.findAll();}


    public FormQuestion findFormQuestionById(Long id) {
        Optional<FormQuestion> byId = formQuestionRepository.findById(id);
        return byId.get();
    }
}
