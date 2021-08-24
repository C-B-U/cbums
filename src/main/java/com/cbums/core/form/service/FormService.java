package com.cbums.core.form.service;

import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.domain.Question;
import com.cbums.core.form.domain.QuestionRepository;
import com.cbums.core.form.dto.FormRequest;
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
    public Long createForm(SessionUser user, FormRequest formRequest) {
        Form form = buildForm(formRequest);
        Member creator = memberService.findByName(user.getName());
        form.setProducer(creator);
        Form result = formRepository.save(form);
        buildQuestion(result, formRequest.getQuestionRequests());
        return result.getFormId();
    }


    private Form buildForm(FormRequest formRequest) {
        return new Form().builder()
                .title(formRequest.getTitle())
                .introduce(formRequest.getIntroduce())
                .openDateTime(formRequest.getOpenDateTime())
                .closeDateTime(formRequest.getCloseDateTime())
                .posterImage(formRequest.getPosterImage())
                .registerNumber(formRequest.getRegisterNumber())
                .build();
    }

    private Form buildForm(Form form, FormRequest formRequest) {
        return form.builder()
                .title(formRequest.getTitle())
                .introduce(formRequest.getIntroduce())
                .openDateTime(formRequest.getOpenDateTime())
                .closeDateTime(formRequest.getCloseDateTime())
                .posterImage(formRequest.getPosterImage())
                .registerNumber(formRequest.getRegisterNumber())
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
    public List<QuestionResponse> findQuestionsByFormId(Long formId) {
        List<Question> questions = findById(formId).getQuestionList();
        return Collections.unmodifiableList(QuestionResponse.listOf(questions));
    }

    @Transactional(readOnly = true)
    public List<FormResponse> findAll() {
        List<Form> forms = formRepository.findAll();
        return Collections.unmodifiableList(FormResponse.listOf(forms));
    }

    @Transactional(readOnly = true)
    public FormResponse findForm(Long formId) {
        Form form = findById(formId);
        return FormResponse.of(form);
    }

    @Transactional(readOnly = true)
    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    private Form findById(Long formId) {
        return formRepository.findById(formId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    @Transactional
    public void updateForm(SessionUser user, Long formId, FormRequest formRequest) {
        Member member = memberService.findByName(user.getName());
        Form form = buildForm(findById(formId), formRequest);
        form.setProducer(member);
        formRepository.save(form);
    }

    @Transactional
    public void deleteForm(Long formId) {
        formRepository.deleteById(formId);
    }
}
