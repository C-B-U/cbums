package com.cbums.core.form.domain;

import com.cbums.core.member.domain.Member;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formId;

    @Column(nullable = false)
    private String title;

    private String introduce;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime openDateTime;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime closeDateTime;

    private Integer registerNumber; // 비모집 지워서인 경우 null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member producer;

    private String posterImage;

    @OneToMany(mappedBy = "form", cascade = CascadeType.REMOVE)
    private List<Question> questionList = new ArrayList<>();

    @Builder
    public Form(String title, String introduce, LocalDateTime openDateTime, LocalDateTime closeDateTime, Integer registerNumber, String posterImage) {
        this.title = title;
        this.introduce = introduce;
        this.openDateTime = openDateTime;
        this.closeDateTime = closeDateTime;
        this.registerNumber = registerNumber;
        this.posterImage = posterImage;
    }

}
