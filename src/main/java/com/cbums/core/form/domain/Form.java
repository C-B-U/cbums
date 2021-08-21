package com.cbums.core.form.domain;

import com.cbums.core.member.domain.Member;
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
    private LocalDateTime openDateTime;

    @Column(nullable = false)
    private LocalDateTime closeDateTime;

    private Integer registerNumber; // 비모집 지워서인 경우 null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member producer;

    @OneToMany(mappedBy = "form", cascade = CascadeType.REMOVE)
    private List<Question> questionList = new ArrayList<Question>();

    @Builder
    public Form(String title, String introduce, LocalDateTime openDateTime, LocalDateTime closeDateTime, Integer registerNumber) {
        this.title = title;
        this.introduce = introduce;
        this.openDateTime = openDateTime;
        this.closeDateTime = closeDateTime;
        this.registerNumber = registerNumber;
    }

}