package com.cbums.api.admin;

import com.cbums.core.form.dto.FormRequest;
import com.cbums.core.form.dto.FormResponse;
import com.cbums.core.form.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/form")
@RequiredArgsConstructor
public class AdminFormController {

    private final FormService formService;

    @GetMapping("")
    public ResponseEntity<List<FormResponse>> getFormList() {
        return ResponseEntity.ok(formService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Void> createForm(Principal principal,
                                           @Valid @RequestBody FormRequest formRequest) {

        Long result = formService.createForm(formRequest, principal.getName());
        return ResponseEntity.created(URI.create("/admin/form/" + result)).build();

    }

    @GetMapping("/{seq}")
    public ResponseEntity<FormResponse> getForm(@PathVariable("seq") Long seq) {
        return ResponseEntity.ok(formService.findForm(seq));
    }

    @PatchMapping("/{seq}")
    public ResponseEntity<Void> updateForm(Principal principal,
                                           @PathVariable("seq") Long seq,
                                           @RequestBody FormRequest formRequest) {

        formService.updateForm(seq, formRequest, principal.getName());

        return ResponseEntity.created(URI.create("/admin/form/" + seq)).build();
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteForm(@PathVariable("seq") Long seq) {

        formService.deleteForm(seq);

        return ResponseEntity.created(URI.create("/admin/form")).build();

    }

}
