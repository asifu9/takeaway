package com.game.server.service;

import com.game.server.entity.dto.PlayerDTO;

/**
 * The Interface PlayerService.
 */
public interface PlayerService {

	/**
	 * Method to Creates the Player
	 *
	 * @param player
	 *            the player
	 * @return the player
	 * @throws Exception
	 *             the exception
	 */
	public PlayerDTO create(PlayerDTO playerDTO) throws Exception;

	/**
	 * Method to Update the given Player
	 *
	 * @param player
	 *            the player
	 * @return the player
	 * @throws Exception
	 *             the exception
	 */
	public PlayerDTO update(PlayerDTO playerDTO) throws Exception;

	/**
	 * Method to Fetch all Players
	 *
	 * @return the java.util. list
	 * @throws Exception
	 *             the exception
	 */
	public java.util.List<PlayerDTO> fetchAll() throws Exception;

	/**
	 * Method to Find Player by Id
	 *
	 * @param id
	 *            the id
	 * @return the player
	 * @throws Exception
	 *             the exception
	 */
	public PlayerDTO findById(int id) throws Exception;

	/**
	 * Method to Delete by player Id
	 *
	 * @param id
	 *            the id
	 * @throws Exception
	 *             the exception
	 */
	public void delete(int id) throws Exception;

	/**
	 * Delete all.
	 *
	 * @throws Exception
	 *             the exception
	 */
	public void deleteAll() throws Exception;

	/**
	 * Method to fetch the user by user name
	 * 
	 * @param username
	 * @return
	 */
	public PlayerDTO findByUserName(String username);
}
