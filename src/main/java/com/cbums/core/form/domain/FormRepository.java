package com.cbums.core.form.domain;

import com.cbums.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {

}