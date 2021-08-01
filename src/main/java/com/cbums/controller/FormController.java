package com.cbums.controller;

import com.cbums.model.Form;
import com.cbums.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormController {

    private final FormService formService;

    @GetMapping("")
    public List<Form> createForm() {
        return formService.findForms();
    }
    @GetMapping("/{seq}")
    public Form formDetailViewPage(@PathVariable("seq") Long seq) {
        return formService.findFormById(seq);
    }

}

