package com.cbums.repository;

import com.cbums.model.FormAnswer;
import com.cbums.model.FormContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormAnswerRepository extends JpaRepository<FormAnswer, Long> {

    @Query("SELECT fa from FormAnswer fa where fa.formContent.form.formId = :formId ")
    public List<FormAnswer> findFormAnswerListByFormId(@Param("formId") Long formId);

}