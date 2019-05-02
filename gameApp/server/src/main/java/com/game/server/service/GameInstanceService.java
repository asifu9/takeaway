package com.game.server.service;

import java.util.List;

import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.enums.GameStatus;

/**
 * The Interface GameInstanceService.
 */
public interface GameInstanceService {

	/**
	 * Method to create a game instance
	 * 
	 * @param gameInstanceDTO
	 * @return
	 * @throws Exception
	 */
	public GameInstanceDTO create(GameInstanceDTO gameInstanceDTO) throws Exception;

	/**
	 * Method to update a game instance
	 * 
	 * @param id
	 * @param gameInstanceDTO
	 * @return
	 * @throws Exception
	 */
	public GameInstanceDTO update(int id, GameInstanceDTO gameInstanceDTO) throws Exception;

	/**
	 * Method to fetch a game instance by id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GameInstanceDTO findById(int id) throws Exception;

	/**
	 * Method to fetch all game instances
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<GameInstanceDTO> findAll() throws Exception;

	/**
	 * Method to delete a game instance by id
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;

	/**
	 * Method to update game instance status
	 * 
	 * @param id
	 * @param running
	 * @return
	 * @throws Exception
	 */
	public boolean updateStatus(int id, GameStatus running) throws Exception;

	/**
	 * Method to update a player status
	 * 
	 * @param id
	 * @param status
	 * @throws Exception
	 */
	public void updatePlayerStatus(int id, GameStatus status) throws Exception;

	/**
	 * Method to delete all the game instances
	 * 
	 * @throws Exception
	 */
	public void deleteAll() throws Exception;

}
