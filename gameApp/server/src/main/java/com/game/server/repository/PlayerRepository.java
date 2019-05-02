package com.game.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.server.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

	Player findByUserName(String username);

}
