package com.client.game.core;

import com.client.game.dto.AuditDTO;
import com.client.game.dto.GameInstanceDTO;

/**
 * Interface to represent game and its actions
 *
 */
public interface IGame {

	/**
	 * Method to start a new Game
	 * 
	 * @param gameInstanceDTO
	 * @throws Exception
	 */
	public void startNewGame(GameInstanceDTO gameInstanceDTO) throws Exception;

	/**
	 * Method to play the game in continues/steps
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void play(AuditDTO data) throws Exception;

	/**
	 * Method to stop the game
	 * 
	 * @param gameId
	 * @throws Exception
	 */
	public void stopGame(int gameId) throws Exception;

	/**
	 * Method to pause the game
	 * 
	 * @param gameId
	 * @throws Exception
	 */
	public void pauseGame(int gameId) throws Exception;

}
