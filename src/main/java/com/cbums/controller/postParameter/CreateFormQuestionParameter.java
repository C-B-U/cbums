package com.cbums.controller.postParameter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateFormQuestionParameter {

    private String content;
    private LocalDateTime openingDatetime;
}
