package com.cbums.model;

import com.cbums.core.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberTagId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id", nullable = false)
    private Tag tag;
}
