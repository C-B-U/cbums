package com.cbums.core.form.domain;

import com.cbums.core.form.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}