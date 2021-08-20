package com.cbums.controller.postParameter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateFormFormParameter {
    private String title;
    private String introduce;
    private LocalDateTime openDateTime;
    private LocalDateTime closeDateTime;
    private Integer registerNumber;
}
