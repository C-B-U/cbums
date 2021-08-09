package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.model.Member;
import com.cbums.repository.FormRepository;
import com.cbums.repository.MemberRepository;
import com.cbums.service.exception.ListEmptyException;
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

    public List<Form> findFormList() throws ListEmptyException {
        List <Form> formList = formRepository.findAll();
        if(formList.isEmpty()) {
            throw new ListEmptyException();
        }
        return formList;
    }

    public Form findFormById(Long id) throws NullPointerException {

       return formRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public Long createForm(Form form) {
        //입력길이 초과시 exception TODO
        HttpSession httpSession = request.getSession();
        Long producerId = (Long) httpSession.getAttribute("login-user");
        Member producer = memberRepository.getById(producerId);
        form.setProducer(producer);
        Form savedForm = formRepository.save(form);

        return savedForm.getFormId();
    }
    //수정 시에 createForm에 id만 부여하면 되는 것 아닐까???
}
