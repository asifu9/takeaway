package com.game.server.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.server.entity.dto.PlayerDTO;
import com.game.server.service.PlayerService;



/**
 * This rest controller represent to create/edit an player details
 */
@RestController
@RequestMapping(value="/player")
public class PlayerApi {

	
	@Autowired
	private PlayerService playerService;
	
	/**
	 * Creates the.
	 *
	 * @param player the player
	 * @return the player
	 * @throws Exception the exception
	 */
	@PostMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private PlayerDTO create(@RequestBody PlayerDTO playerDTO) throws Exception{
		return playerService.create(playerDTO);
	}
	
	/**
	 * Gets the all player info
	 *
	 * @return the a ll
	 * @throws Exception the exception
	 */
	@GetMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<PlayerDTO> getALl() throws Exception{
		
		return playerService.fetchAll();
	}
	
	/**
	 * Api to delete the player info for given player ID
	 *
	 * @return the a ll
	 * @throws Exception the exception
	 */
	@DeleteMapping(value="/{playerId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private void delete(@PathVariable("playerId") int playerId) throws Exception{
		
		 playerService.delete(playerId);
	}
	
	/**
	 * Method to fetch player info for given user name
	 *
	 * @throws Exception the exception
	 */
	@GetMapping(value="/username/{username}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private PlayerDTO findByUserName(@PathVariable("username") String username) throws Exception{
		
		return playerService.findByUserName(username);
	}
}
