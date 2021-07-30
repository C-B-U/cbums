package com.cbums.service;

import com.cbums.model.Form;
import com.cbums.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;

    public List<Form> getForms() {
        return formRepository.findAll();
    }
}
