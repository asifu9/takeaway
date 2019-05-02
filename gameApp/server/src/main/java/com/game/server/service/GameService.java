package com.game.server.service;

import java.util.List;

import com.game.server.entity.dto.GameDTO;
import com.game.server.enums.GameType;

/**
 * Service class to represent Game operations.
 *
 */
public interface GameService {

	/**
	 * Method to create a new game info
	 * 
	 * @param playerDTO
	 * @return
	 * @throws Exception
	 */
	public GameDTO create(GameDTO playerDTO) throws Exception;

	/**
	 * Method to update a new game info
	 * 
	 * @param gameId
	 * @param gameDTO
	 * @return
	 * @throws Exception
	 */
	public GameDTO update(int gameId, GameDTO gameDTO) throws Exception;

	/**
	 * Method to fetch games by game type
	 * 
	 * @param gameType
	 * @return
	 * @throws Exception
	 */
	public List<GameDTO> findByGameType(GameType gameType) throws Exception;

	/**
	 * Method to fetch games by game id
	 * 
	 * @param gameId
	 * @return
	 * @throws Exception
	 */
	public GameDTO findById(int gameId) throws Exception;

	/**
	 * Method to fetch all games info
	 * 
	 * @return
	 */
	public List<GameDTO> findAll();

	/**
	 * Method to delete game info by id
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;

	/**
	 * Method to delete all the games
	 * 
	 * @throws Exception
	 */
	public void deleteAll() throws Exception;
}
