package com.game.server.game.number;

import com.game.server.entity.dto.GameInstanceDTO;

/**
 * Interface to represent game action
 *
 */
public interface IGameUserAction {

	/**
	 * Method to start a game
	 * 
	 * @param instance
	 * @param currentUserId
	 * @throws Exception
	 */
	void startGame(GameInstanceDTO instance, int currentUserId) throws Exception;

	/**
	 * Method to pause a game
	 * 
	 * @throws Exception
	 */
	void pauseGame() throws Exception;

	/**
	 * Method to resume a game
	 * 
	 * @throws Exception
	 */
	void resumeGame() throws Exception;

	/**
	 * Method to stop a game
	 * 
	 * @throws Exception
	 */
	void stopGame() throws Exception;

}
