package com.cbums.core.form.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Transactional
    @Modifying
    @Query("DELETE  FROM Question q WHERE q.form.formId = :formId")
    void deleteByFormId(@Param("formId")Long formId);
}