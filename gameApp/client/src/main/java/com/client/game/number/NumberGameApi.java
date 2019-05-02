package com.client.game.number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.game.core.IGame;
import com.client.game.dto.GameInstanceDTO;

/**
 * Rest controller class for number game API
 * 
 */
@RestController
@RequestMapping(value = "/game/number")
public class NumberGameApi {

	@Autowired
	private IGame game;

	@Autowired
	MessageController controller;

	/**
	 * Method to initiate new game or join existing game.
	 * 
	 * @param gameInstanceDTO
	 * @throws Exception
	 */
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private void initiateOrJoinGame(@RequestBody GameInstanceDTO gameInstanceDTO) throws Exception {
		game.startNewGame(gameInstanceDTO);
	}

	/**
	 * Method to stop the game
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private GameInstanceDTO stopGame() throws Exception {
		throw new Exception("Not Implemented");
	}
}
