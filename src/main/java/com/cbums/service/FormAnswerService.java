package com.cbums.service;

import com.cbums.repository.FormAnswerRepository;
import com.cbums.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FormAnswerService {
    private final FormAnswerRepository formAnswerRepository;
    //MemberService를 받아야 하나 Repo를 받아야하나... TODO
    private final MemberRepository memberRepository;

    public void createFormAnswer(Long formId,String writer, Map<Long,String> answer) {

    }
}
