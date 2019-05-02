package com.game.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.server.entity.GameInstance;
import com.game.server.entity.GamePlayer;

/**
 * The Interface GamePlayerRepository.
 */
@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, Integer> {

	/**
	 * Find by game instance.
	 *
	 * @param gameInstanceId
	 *            the game instance id
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public List<GamePlayer> findByGameInstanceId(GameInstance gameId) throws Exception;

}