package com.game.server.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.server.entity.NumberGameAudit;
import com.game.server.entity.dto.AuditDTO;
import com.game.server.service.NumberGameAuditService;

/**
 * Rest Controller class to create Audit entry of each move of the player
 */
@RestController
@RequestMapping(value = "/number/game/audit")
public class NumberGameAuditApi {

	@Autowired
	private NumberGameAuditService auditService;

	/**
	 * API to create new game.
	 *
	 * @param game
	 *            the game
	 * @return the game
	 * @throws Exception
	 *             the exception
	 */
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private void create(@RequestBody AuditDTO game) throws Exception {

		auditService.create(game);

	}

	/**
	 * API method to fetch all the game audit/step for given game instance ID
	 * 
	 * @param gameInstanceId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/gameinstanceid/{gameInstanceId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<AuditDTO> getByGameInstanceId(@PathVariable(value = "gameInstanceId") int gameInstanceId)
			throws Exception {
		List<NumberGameAudit> list = auditService.findByGameInstanceId(gameInstanceId);
		if (null != list) {
			return list.stream()
					.map(a -> new AuditDTO(a.getNumber(), a.getUsedOptionNumber(), a.getResultNumber(), "",
							a.getGameStatus(), a.getSendByPlayerId(), 0, gameInstanceId, "", 0))
					.collect(Collectors.toList());
		}
		return null;
	}

}
