package com.game.server.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.server.entity.dto.GameDTO;
import com.game.server.enums.GameType;
import com.game.server.service.GameService;



/**
*  Rest Controller class to create, update and list  Game API.
*/
@RestController
@RequestMapping(value="/game")
public class GameApi {

	@Autowired
	private GameService gameService;
	
	
	/**
	 * API to create new game.
	 *
	 * @param game the game
	 * @return the game
	 * @throws Exception the exception
	 */
	@PostMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private GameDTO create(@RequestBody GameDTO game) throws Exception{

		return gameService.create(game);
		
	}
	
	/**
	 * API to update game.
	 *
	 * @param gameId the game id
	 * @param game the game
	 * @return the game
	 * @throws Exception the exception
	 */
	@PutMapping(value="/{gameId}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private GameDTO update(
				@PathVariable(value="gameId") int gameId, 
				@RequestBody GameDTO game) throws Exception{

		return gameService.update(gameId,game);
		
	}
	
	/**
	 * API to find the games by game Type.
	 *
	 * @param gameType the game type
	 * @return the list
	 * @throws Exception the exception
	 */
	@GetMapping(value="/type/{gameType}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<GameDTO> findByGameTYpe(@PathVariable("gameType") GameType gameType) throws Exception{
		
		return gameService.findByGameType(gameType);
		
	}
	
	/**
	 * API to find all the games.
	 *
	 * @return the list
	 * @throws Exception the exception
	 */
	@GetMapping(value="/",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<GameDTO> findAll() throws Exception{
		
		return gameService.findAll();
		
		
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the game
	 * @throws Exception the exception
	 */
	@GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private GameDTO findById(@PathVariable("id") int id) throws Exception{
		
		return gameService.findById(id);
		
	}
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @throws Exception the exception
	 */
	@DeleteMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private void deleteById(@PathVariable("id") int id) throws Exception{
		
		 gameService.delete(id);
		
	}
}

