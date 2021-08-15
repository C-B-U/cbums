package com.cbums.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="member_id", nullable = false)
    private Member producer;

    @OneToMany(mappedBy = "form", cascade = CascadeType.REMOVE)
    private List<FormContent> formContentList = new ArrayList<FormContent>();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
