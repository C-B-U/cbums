package com.cbums.core.form.service;

import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.dto.CreateFormRequest;
import com.cbums.core.form.dto.FormResponse;
import com.cbums.core.member.domain.Member;
import com.cbums.core.form.domain.FormRepository;
import com.cbums.core.member.service.MemberService;
import com.cbums.service.exception.NotLoginedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final MemberService memberService;
    @Transactional
    public Long createForm(CreateFormRequest createFormRequest, String producer) {
        Form form = buildForm(createFormRequest);
        Member creater = memberService.findByEmail(producer);
        form.setProducer(creater);
        Form result = formRepository.save(form);
        return result.getFormId();
    }

    private Form buildForm(CreateFormRequest createFormRequest) {
        return Form.builder()
                .title(createFormRequest.getTitle())
                .introduce(createFormRequest.getIntroduce())
                .openDateTime(createFormRequest.getOpenDateTime())
                .closeDateTime(createFormRequest.getCloseDateTime())
                .registerNumber(createFormRequest.getRegisterNumber())
                .build();
    }

    public List<FormResponse> findAll() {
        List <Form> forms = formRepository.findAll();
        return Collections.unmodifiableList(FormResponse.listOf(forms));
    }

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
