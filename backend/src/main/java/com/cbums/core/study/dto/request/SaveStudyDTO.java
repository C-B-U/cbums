package com.cbums.core.study.dto.request;

import com.cbums.core.common.domain.RequestDTO;

public class SaveStudyDTO implements RequestDTO {

    private String name;
    private String rule;
    private String additionalExplain;

    @Override
    public Object toEntity(Object o) {
        return null;
    }
}
