package com.cbums.service;

import com.cbums.model.FormQuestion;
import com.cbums.model.Member;
import com.cbums.repository.FormQuestionRepository;
import com.cbums.repository.MemberRepository;
import com.cbums.service.exception.NotLoginedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormQuestionService {
    private final FormQuestionRepository formQuestionRepository;
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;

    public Long createFormQuestion(FormQuestion formQuestion) throws NotLoginedException {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("login-user") == null) throw new NotLoginedException();
        Long producerId = (Long)httpSession.getAttribute("login-user");
        Member producer = memberRepository.getById(producerId);
        formQuestion.setProducer(producer);
        FormQuestion savedFormQuestion = formQuestionRepository.save(formQuestion);

        return savedFormQuestion.getFormQuestionId();
    }

    public List<FormQuestion> findFormQuestions() {
        List<FormQuestion> formQuestionList = formQuestionRepository.findAll();
        if(formQuestionList.isEmpty()) throw new NoSuchElementException();
        return formQuestionList;
    }


    public FormQuestion findFormQuestionById(Long id) {
        return  formQuestionRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
