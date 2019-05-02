package com.client.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.client.game.dto.AuditDTO;
import com.client.game.dto.GameDTO;
import com.client.game.service.IGame;
import com.client.game.util.RestTemplateUtil;

@Service
public class Game implements IGame {

	@Autowired
	private RestTemplateUtil restTemplateUtil;

	@Value("${core.server.url}")
	private String coreServerUrl;

	@Override
	public List<GameDTO> fetchAllGames() throws Exception {
		return restTemplateUtil.fetch(coreServerUrl + "/game/", GameDTO.class);
	}

	public void setRestTemplateUtil(RestTemplateUtil restTemplateUtil) {
		this.restTemplateUtil = restTemplateUtil;
	}

	@Override
	public List<AuditDTO> fetchGameInstanceById(int gameInstanceId) throws Exception {
		return restTemplateUtil.fetch(coreServerUrl + "/number/game/audit/gameinstanceid/" + gameInstanceId,
				AuditDTO.class);
	}

}
