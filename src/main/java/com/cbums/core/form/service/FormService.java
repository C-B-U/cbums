package com.cbums.core.form.service;

import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.domain.QuestionRepository;
import com.cbums.core.form.dto.CreateFormRequest;
import com.cbums.core.form.dto.FormResponse;
import com.cbums.core.form.dto.QuestionRequest;
import com.cbums.core.form.dto.QuestionResponse;
import com.cbums.core.member.domain.Member;
import com.cbums.core.form.domain.FormRepository;
import com.cbums.core.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    @Transactional
    public Long createForm(CreateFormRequest createFormRequest, String producer) {
        Form form = buildForm(createFormRequest);
        Member creator = memberService.findByEmail(producer);
        form.setProducer(creator);
        Form result = formRepository.save(form);
        buildQuestion(result, createFormRequest.getQuestionRequests());
        return result.getFormId();
    }


    private Form buildForm(CreateFormRequest createFormRequest) {
        return new Form().builder()
                .title(createFormRequest.getTitle())
                .introduce(createFormRequest.getIntroduce())
                .openDateTime(createFormRequest.getOpenDateTime())
                .closeDateTime(createFormRequest.getCloseDateTime())
                .registerNumber(createFormRequest.getRegisterNumber())
                .build();
    }

    private void buildQuestion(Form form, List<QuestionRequest> questionRequests) {
        for (QuestionRequest q : questionRequests) {
            Question question = new Question().builder()
                    .content(q.getContent())
                    .form(form)
                    .build();
            questionRepository.save(question);
        }
    }

    @Transactional(readOnly = true)
    public List<QuestionResponse> findQuestionAll() {
        List <Question> questions = questionRepository.findAll();
        return Collections.unmodifiableList(QuestionResponse.listOf(questions));
    }

    @Transactional(readOnly = true)
    public List<FormResponse> findAll() {
        List <Form> forms = formRepository.findAll();
        return Collections.unmodifiableList(FormResponse.listOf(forms));
    }

    @Transactional(readOnly = true)
    public FormResponse findForm(Long formId) {
        Form form = findById(formId);
        return FormResponse.of(form);
    }

    private Form findById(Long formId) {
        return formRepository.findById(formId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }
    @Transactional
    public void deleteForm(Long formId) {
        formRepository.deleteById(formId);
    }
}
