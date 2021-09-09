package com.cbums.core.member.domain;

import com.cbums.core.member.domain.Member;
import com.cbums.core.tag.domain.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public MemberTag(Member member, Tag tag) {
        this.member = member;
        this.tag = tag;
    }
}
