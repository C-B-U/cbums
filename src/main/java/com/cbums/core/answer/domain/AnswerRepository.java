package com.cbums.core.answer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.question.form.formId = :formId")
    List<Answer> findAnswersByFormId(@Param("formId")Long formId);

    @Query("SELECT a FROM Answer a WHERE a.member.classNumber = :memberClassNumber AND a.question.form.formId = :formId")
    List<Answer> findAnswersByMemberClassNumberAndFormId(@Param("memberClassNumber") String memberClassNumber,
                                     @Param("formId")Long formId);
}