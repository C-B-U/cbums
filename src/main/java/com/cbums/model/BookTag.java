package com.cbums.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BookTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookTagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id", nullable = false)
    private Tag tag;
}
