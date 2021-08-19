package com.cbums.core.form.service;

import com.cbums.core.form.domain.Question;
import com.cbums.core.member.domain.Member;
import com.cbums.core.form.domain.FormQuestionRepository;
import com.cbums.service.exception.NotLoginedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FormQuestionService {
    private final FormQuestionRepository formQuestionRepository;
    private final MemberService memberService;
    private final HttpServletRequest request;

    public Long createFormQuestion(Question question) throws NotLoginedException {
        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("login-user") == null) throw new NotLoginedException();
        Long producerId = (Long)httpSession.getAttribute("login-user");
        Member producer =memberService.findMemberById(producerId);
        question.setProducer(producer);
        Question savedQuestion = formQuestionRepository.save(question);

        return savedQuestion.getFormQuestionId();
    }

    public List<Question> findFormQuestions() {
        List<Question> questionList = formQuestionRepository.findAll();
        if(questionList.isEmpty()) throw new NoSuchElementException();
        return questionList;
    }


    public Question findFormQuestionById(Long id) {
        return  formQuestionRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
