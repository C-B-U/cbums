package com.cbums.core.form.domain;

import com.cbums.core.form.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT fa FROM Answer fa WHERE fa.formContent.form.formId = :formId ")
    public List<Answer> findFormAnswerListByFormId(@Param("formId") Long formId);

    @Query("SELECT fa FROM Answer fa WHERE fa.member.memberId = :memberId AND fa.formContent.form.formId = :formId")
    public List<Answer> findFormAnswerByFormIdAndMemberId(@Param("formId") Long formId, @Param("memberId") Long memberId);
}