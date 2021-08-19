package com.cbums;

import com.cbums.model.Form;
import com.cbums.model.FormQuestion;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.UserRoleType;

import java.time.LocalDateTime;
import java.util.Random;

public class RandomValue {

    private final static int LEFT_LIMIT = 48; // numeral '0'
    private final static int RIGHT_LIMIT = 122; // letter 'z'
    private final static int TARGET_STRING_LENGTH = 7;

    public static Member getMember() {
        Member member = Member.builder()
                .name(getGeneratedString())
                .department(getGeneratedString())
                .email(getGeneratedString())
                .classNumber(getGeneratedInteger().toString())
                .phoneNumber(getGeneratedInteger(10000000,99999999).toString())
                .build();

        return member;
    }

    public static Form getForm() {
        Form form = Form.builder()
                .introduce(getGeneratedString())
                .title(getGeneratedString())
                .registerNumber(getGeneratedInteger(1,10))
                .openDateTime(LocalDateTime.now())
                .closeDateTime(LocalDateTime.now())
                .build();

        return form;
    }


    public static FormQuestion getFormQuestion() {
        FormQuestion formQuestion = FormQuestion.builder()
                .content(getGeneratedString())
                .openingDatetime(LocalDateTime.now())
                .build();

        return formQuestion;
    }


    public static Integer getGeneratedInteger() {
        Random random = new Random();
        return random.nextInt();
    }

    public static Integer getGeneratedInteger(int min, int max) {
        Random random = new Random();

        return random.nextInt(max - min) + min;
    }

    public static String getGeneratedString() {
        Random random = new Random();
        return random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(TARGET_STRING_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
