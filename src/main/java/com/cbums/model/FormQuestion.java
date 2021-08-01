package com.cbums.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class FormQuestion {
    @Id
    @GeneratedValue(FormContent = GenerationType.IDENTITY)
    private Long formQuestionId;

    private String content;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member producer;

    @Column(nullable = false)
    private LocalDateTime openingDatetime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
