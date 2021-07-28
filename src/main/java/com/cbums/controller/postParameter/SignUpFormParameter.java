package com.cbums.controller.postParameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpFormParameter {
    private String password;
    private String passwordCheck;
    private String introduce;
    private String image;
}
