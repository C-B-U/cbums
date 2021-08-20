package com.cbums.model;

import com.cbums.core.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSubscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private LocalDateTime subscriptionDatetime;
}
