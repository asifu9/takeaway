package com.client.game.number;

import java.util.ArrayList;
import java.util.List;

import com.client.game.dto.AuditDTO;
import com.client.game.dto.GameInstanceDTO;

public class NumberGameSession {

	GameInstanceDTO gameInstance;
	List<AuditDTO> audit;

	public List<AuditDTO> getPlayerMoves() {
		return audit == null ? new ArrayList<AuditDTO>() : audit;
	}

	public GameInstanceDTO getGameInstance() {
		return gameInstance;
	}

	public void setGameInstance(GameInstanceDTO gameInstance) {
		this.gameInstance = gameInstance;
	}

}
