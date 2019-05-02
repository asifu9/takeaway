package com.game.server.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.server.entity.dto.GamePlayerDTO;
import com.game.server.service.GamePlayerService;


/**
 * The Class GamePlayerApi.
 */
@RestController
@RequestMapping(value="/game-player")
public class GamePlayerApi {

	@Autowired
	private GamePlayerService gamePlayerService;
	
	/**
	 * API to Create the game player.
	 *
	 */
	@PostMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private GamePlayerDTO create(@RequestBody GamePlayerDTO gamePlayerDTO) throws Exception{
		return gamePlayerService.create(gamePlayerDTO);
	}
	

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@GetMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<GamePlayerDTO> findAll() throws Exception{
		
		return gamePlayerService.findAll();
	}
}
