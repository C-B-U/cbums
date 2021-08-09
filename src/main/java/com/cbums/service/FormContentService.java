package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.model.FormContent;
import com.cbums.model.FormQuestion;
import com.cbums.repository.FormContentRepository;
import com.cbums.repository.FormQuestionRepository;
import com.cbums.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FormContentService {
    private final FormContentRepository formContentRepository;
    private final FormRepository formRepository;
    private final FormQuestionRepository formQuestionRepository;

    public List<Long> createFormContent(Long formId, List<Long> formQuestionIdList) {

        List<Long> formContendId = new ArrayList<>();
        FormContent formContent = null;
        Form form = formRepository.getById(formId);
        for(Long k : formQuestionIdList) {
            formContent = new FormContent();
            formContent.setForm(form);
            formContent.setFormQuestion(formQuestionRepository.getById(k));
            formContendId.add(formContentRepository.save(formContent).getFormContentId());
        }

        return formContendId;
    }

    public List<FormContent> findFormContentListByFormId(Long FormId){
        List<FormContent> formContentList = formContentRepository.findFormContentListByFormId(FormId);
        if(formContentList.isEmpty()) throw new NoSuchElementException();
        return formContentList;
    }
}
