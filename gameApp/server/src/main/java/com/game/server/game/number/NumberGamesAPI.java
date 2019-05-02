package com.game.server.game.number;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.server.entity.dto.GameInstanceDTO;

/**
 * This class rest end point will be called by client to initiate or 
 * to join a game.
 */
@RestController
@RequestMapping(value = "/manage/game")
public class NumberGamesAPI {

	@Autowired
	private IGameUserAction gameUserAction;

	/**
	 * API to create new game or join existing game. 
	 * If a user already there in Queue then both will start playing game.
	 * else this user will be in queue waiting for second player.
	 *
	 * @param game the game
	 * @return the game
	 * @throws Exception the exception
	 */
	@PostMapping(value = "/{currentUserId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private void createOrJoinGame(@RequestBody GameInstanceDTO gameInstance,
			@PathVariable(value = "currentUserId") int currentUserId) throws Exception {

		gameUserAction.startGame(gameInstance, currentUserId);

	}

}
