package com.cbums.core.book.domain;

import com.cbums.core.book.domain.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTagRepository extends JpaRepository<BookTag, Long> {

}