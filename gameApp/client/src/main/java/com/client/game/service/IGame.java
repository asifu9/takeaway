package com.client.game.service;

import java.util.List;

import com.client.game.dto.AuditDTO;
import com.client.game.dto.GameDTO;

/**
 * 
 * Interface to represent Game entity operation
 *
 */
public interface IGame {

	/**
	 * Method to fetch all the games
	 * 
	 * @return
	 * @throws Exception
	 */
	List<GameDTO> fetchAllGames() throws Exception;

	/**
	 * Method to fetch all game audit data by game instance id
	 * 
	 * @param gameInstanceId
	 * @return
	 * @throws Exception
	 */
	List<AuditDTO> fetchGameInstanceById(int gameInstanceId) throws Exception;

}
