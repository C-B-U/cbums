package com.cbums.controller.admin;

import com.cbums.controller.FormController;
import com.cbums.model.Form;
import com.cbums.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/form/**")
@RequiredArgsConstructor
public class AdminFormController {

    private final FormService formService;

    @PostMapping("/")
    public Long formViewPage(FormController formController) {
        Form form = new Form();
        return formService.createForm(form);
    }
}
