package com.game.server.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.service.GameInstanceService;


/**
 *  Rest Controller class to create, update and list  Game Instance API.
 */
@RestController
@RequestMapping(value="/game-instance")
public class GameInstanceApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(GameInstanceApi.class);
	
	@Autowired
	private GameInstanceService gameInstanceService;
	
	/**
	 * API to create new game instance before he start to play
	 *
	 */
	@PostMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private GameInstanceDTO create(@RequestBody GameInstanceDTO gameInstanceDTO) throws Exception{
		LOGGER.debug("creating new game instance ");
		return gameInstanceService.create(gameInstanceDTO);
	}
	
	/**
	 * Gets the all the game instances
	 *
	 * @return list of all game instances
	 * @throws Exception the exception
	 */
	@GetMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<GameInstanceDTO> getALl() throws Exception{
		
		return gameInstanceService.findAll();
	}
	
	/**
	 * Gets the all the game instances
	 *
	 * @return list of all game instances
	 * @throws Exception the exception
	 */
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private GameInstanceDTO getById(@PathVariable(value="id") int id) throws Exception{
		
		return gameInstanceService.findById(id);
	}
}
