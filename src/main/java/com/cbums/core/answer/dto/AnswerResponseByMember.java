package com.cbums.core.answer.dto;

import com.cbums.core.answer.domain.Answer;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.dto.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponseByMember {
    private Map<MemberResponse, List<AnswerResponse>> answersByMember;

    public static AnswerResponseByMember of(List<Answer> answers) {

        Map<Member,  List<Answer>> answerTemp = answers.stream()
                .collect(Collectors.groupingBy(Answer::getMember));
        List <Member> answerMembers = new ArrayList<>(answerTemp.keySet());

        Map<MemberResponse, List<AnswerResponse>> result = new HashMap();

        for(Member m : answerMembers) {
            result.put(MemberResponse.of(m),
                    AnswerResponse.listOf(answerTemp.get(m)));
        }

        return new AnswerResponseByMember(result);
    }
}
