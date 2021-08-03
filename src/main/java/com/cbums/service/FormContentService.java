package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.model.FormContent;
import com.cbums.model.FormQuestion;
import com.cbums.repository.FormContentRepository;
import com.cbums.repository.FormQuestionRepository;
import com.cbums.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormContentService {
    private final FormContentRepository formContentRepository;
    private final FormRepository formRepository;
    private final FormQuestionRepository formQuestionRepository;

    public void createFormContent(Long formId, List<Long> formQuestionIdList) {

        FormContent formContent = new FormContent();
        Form form = formRepository.getById(formId);
        FormQuestion formQuestion = null;
        for(Long k : formQuestionIdList) {
            formContent.setFormQuestion(formQuestionRepository.getById(k));
            formContentRepository.save(formContent);
        }
    }

    public List<FormContent> findFormContentListByFormId(Long FormId){
        return formContentRepository.findFormContentListByFormId(FormId);
    }
}
