package com.cbums.core.answer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a WHERE a.question.form.formId = :formId")
    List<Answer> findAnswersByFormId(@Param("formId")Long formId);

    @Query("SELECT a FROM Answer a WHERE a.member.memberId = :memberId AND a.question.form.formId = :formId")
    List<Answer> findAnswersByMemberClassNumberAndFormId(@Param("memberId") Long memberId,
                                     @Param("formId")Long formId);
}