package com.cbums.repository;

import com.cbums.model.FormContent;
import com.cbums.model.FormQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormContentRepository extends JpaRepository<FormContent, Long> {


    @Query("SELECT FormQuestion from FormContent fc where fc.form.formId = :formId ")
    public List<FormQuestion> findFormContentQuestionListByFormId(@Param("formId") Long formId);

}