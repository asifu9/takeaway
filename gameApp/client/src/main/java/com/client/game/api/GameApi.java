package com.client.game.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.game.dto.AuditDTO;
import com.client.game.dto.GameDTO;
import com.client.game.number.MessageController;
import com.client.game.service.IGame;

/**
 * Rest controller class to manage all the games activity. As of now we only
 * fetching all the games, but later we can add here to create a new game,
 * update a game and delete a game etc.
 */
@RestController
@RequestMapping(value = "/game")
public class GameApi {

	@Autowired
	private IGame manageGame;

	@Autowired
	MessageController messagingTemplate;

	/**
	 * Api endpoint to get all the existing games
	 *
	 */
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<GameDTO> getALl() throws Exception {
		return manageGame.fetchAllGames();
	}

	/**
	 * Api endpoint to fetch all audit data for given game instance id
	 * 
	 * @param gameInstanceId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/gameinstanceId/{gameInstanceId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<AuditDTO> getGameAuditData(@PathVariable(value = "gameInstanceId") int gameInstanceId)
			throws Exception {
		return manageGame.fetchGameInstanceById(gameInstanceId);
	}

}
