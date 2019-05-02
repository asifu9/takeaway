package com.client.game.service;

import com.client.game.dto.PlayerDTO;

/**
 * Interface to represent players entity operation
 *
 */
public interface IPlayers {

	/**
	 * Method to fetch player info by user name
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	PlayerDTO getByUserName(String userName) throws Exception;
}
