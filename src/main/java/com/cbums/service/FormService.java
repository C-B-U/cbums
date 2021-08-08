package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.model.Member;
import com.cbums.repository.FormRepository;
import com.cbums.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;

    public List<Form> findForms() {
        return formRepository.findAll();
    }

    public Form findFormById(Long id) {
        Optional<Form> byId = formRepository.findById(id);
        return byId.get();
    }

    public Long createForm(Form form) {
        HttpSession httpSession = request.getSession();
        Long producerId = (Long)httpSession.getAttribute("login-user");
        Member producer = memberRepository.getById(producerId);
        form.setProducer(producer);
        Form savedForm = formRepository.save(form);

        return savedForm.getFormId();
    }
}
