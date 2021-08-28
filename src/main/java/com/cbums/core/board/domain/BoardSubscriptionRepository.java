package com.cbums.core.board.domain;

import com.cbums.core.board.domain.BoardSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardSubscriptionRepository extends JpaRepository<BoardSubscription, Long> {

}