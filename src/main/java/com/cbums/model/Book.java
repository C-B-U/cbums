package com.cbums.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_member_id", nullable = false)
    private Member owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="borrower_member_id")
    private Member borrower;

    @Column(nullable = false)
    private LocalDateTime registerDatetime;
}
