package com.cbums.core.member.domain;

import com.cbums.core.common.domain.BaseTimeEntity;
import com.cbums.core.tag.domain.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoleType role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    @Builder
    public Member(String name, String email, String picture, UserRoleType role, AuthProvider provider) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.provider = provider;
    }

    @OneToOne
    @JoinColumn(name = "member_detail_id")
    private MemberDetail memberDetail;

    public void setMemberDetail(MemberDetail memberDetail) {
        this.memberDetail = memberDetail;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    public List<MemberTag> memberTags = new ArrayList<>();

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void setRole(UserRoleType role) {
        this.role = role;
    }
}
