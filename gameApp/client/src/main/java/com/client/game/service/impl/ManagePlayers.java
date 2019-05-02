package com.client.game.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.client.game.dto.PlayerDTO;
import com.client.game.service.IPlayers;
import com.client.game.util.RestTemplateUtil;

@Service
public class ManagePlayers implements IPlayers {

	@Autowired
	private RestTemplateUtil restTemplateUtil;

	@Value("${core.server.url}")
	private String coreServerUrl;

	@Override
	public PlayerDTO getByUserName(String userName) throws Exception {
		return (PlayerDTO) restTemplateUtil.fetchData(coreServerUrl + "/player/username/" + userName, PlayerDTO.class);
	}

}
