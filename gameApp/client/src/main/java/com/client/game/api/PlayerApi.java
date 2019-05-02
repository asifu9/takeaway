package com.client.game.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.game.dto.PlayerDTO;
import com.client.game.service.IPlayers;




/**
 * The Class PlayerApi.
 */
@RestController
@RequestMapping(value="/player")
public class PlayerApi {

	@Autowired
	private IPlayers managePlayers;
	
	
	@GetMapping(value="/{userName}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private PlayerDTO getByUserName(@PathVariable(value="userName") String userName) throws Exception{
		
		return managePlayers.getByUserName(userName);
	}
	

}
