package com.game.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.server.entity.Game;
import com.game.server.enums.GameType;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

	/**
	 * Find by game types.
	 *
	 * @param gameTypes
	 *            the game types
	 * @return the list
	 */
	List<Game> findByGameType(GameType gameType);
}
