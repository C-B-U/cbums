package com.cbums.core.form.dto;

import com.cbums.core.form.domain.Form;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class FormResponse {

    private String title;
    private String introduce;
    private LocalDateTime openDateTime;
    private LocalDateTime closeDateTime;
    private Integer registerNumber;
    private String posterImage;

    public static FormResponse of(Form form) {
        return new FormResponse(form.getTitle(),
                form.getIntroduce(),
                form.getOpenDateTime(),
                form.getCloseDateTime(),
                form.getRegisterNumber(),
                form.getPosterImage());
    }

    public static List<FormResponse> listOf(List<Form> forms) {
        return forms.stream()
                .map(FormResponse::of)
                .collect(Collectors.toList());
    }

}
