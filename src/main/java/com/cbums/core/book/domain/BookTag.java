package com.cbums.core.book.domain;

import com.cbums.core.tag.domain.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
