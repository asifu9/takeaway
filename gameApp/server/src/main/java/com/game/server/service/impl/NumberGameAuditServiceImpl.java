package com.game.server.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.server.entity.GameInstance;
import com.game.server.entity.NumberGameAudit;
import com.game.server.entity.dto.AuditDTO;
import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.entity.dto.GamePlayerDTO;
import com.game.server.enums.GameStatus;
import com.game.server.repository.NumberGameAuditRepository;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GamePlayerService;
import com.game.server.service.GameUtil;
import com.game.server.service.NumberGameAuditService;

/**
 * The Class GameInstanceServiceImpl.
 */
@Service
@Transactional
public class NumberGameAuditServiceImpl implements NumberGameAuditService {

	@Autowired
	private NumberGameAuditRepository numberGameAuditRepo;

	@Autowired
	private GameInstanceService gameInstanceService;

	@Autowired
	private GamePlayerService gamePlayerService;

	@Autowired
	private GameUtil gameUtil;

	@Override
	public NumberGameAudit create(NumberGameAudit numberGameStep) throws Exception {
		return numberGameAuditRepo.save(numberGameStep);
	}

	@Override
	public List<NumberGameAudit> findByGameInstanceId(int gameInstanceId) throws Exception {
		return numberGameAuditRepo.findByGameInstanceId(gameInstanceId);
	}

	@Override
	public AuditDTO create(AuditDTO game) throws Exception {
		NumberGameAudit step = new NumberGameAudit();
		step.setGameInstanceId(game.getGameInstanceId());
		step.setGameStatus(game.getGameStatus());
		step.setNumber(game.getNumber());
		step.setSendByPlayerId(game.getCurrentPlayerId());
		step.setResultNumber(game.getResult());
		step.setUsedOptionNumber(game.getCalculatedNumber());
		if (game.getGameStatus() == GameStatus.END || game.getGameStatus() == GameStatus.CANCELED) {
			// update the GameInstance
			GameInstanceDTO gameInstanceDto = gameInstanceService.findById(game.getGameInstanceId());
			gameInstanceDto.setStatus(game.getGameStatus());
			if (game.getGameStatus() == GameStatus.END) {
				gameInstanceDto.setWonByPlayerId(step.getSendByPlayerId());
			}
			gameInstanceService.update(step.getGameInstanceId(), gameInstanceDto);

			// now lets update the game player status
			List<GamePlayerDTO> players = gamePlayerService
					.findByGameInstanceId(gameUtil.convert(gameInstanceDto, GameInstance.class));
			for (GamePlayerDTO dto : players) {
				updatePlayer(dto, game.getGameStatus());
			}
		}
		create(step);
		return null;
	}

	void updatePlayer(GamePlayerDTO dto, GameStatus status) throws Exception {
		dto.setStatus(status);
		gamePlayerService.update(dto);
	}

	@Override
	public void deleteAll() {
		numberGameAuditRepo.deleteAll();
	}

}
