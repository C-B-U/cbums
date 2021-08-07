package com.cbums.repository;

import com.cbums.model.FormAnswer;
import com.cbums.model.FormContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormAnswerRepository extends JpaRepository<FormAnswer, Long> {

    @Query("SELECT fa FROM FormAnswer fa WHERE fa.formContent.form.formId = :formId ")
    public List<FormAnswer> findFormAnswerListByFormId(@Param("formId") Long formId);

    @Query("SELECT fa FROM FormAnswer fa WHERE fa.member.memberId = :memberId AND fa.formContent.form.formId = :formId")
    public List<FormAnswer> findFormAnswerByFormIdAndMemberId(@Param("formId") Long formId, @Param("memberId") Long memberId);
}