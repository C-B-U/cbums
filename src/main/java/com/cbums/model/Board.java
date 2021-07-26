package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="member_id", nullable = false)
    private Member producer;

   // @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime openingDatetime;

}
