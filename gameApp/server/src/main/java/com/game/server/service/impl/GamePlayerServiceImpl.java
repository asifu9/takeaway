package com.game.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.server.entity.GameInstance;
import com.game.server.entity.GamePlayer;
import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.entity.dto.GamePlayerDTO;
import com.game.server.entity.dto.PlayerDTO;
import com.game.server.repository.GamePlayerRepository;
import com.game.server.service.GamePlayerService;
import com.game.server.service.GameUtil;

/**
 * The Class GamePlayerServiceImpl.
 */
@Service
@Transactional
public class GamePlayerServiceImpl implements GamePlayerService {

	@Autowired
	private GamePlayerRepository gamePlayerRepository;

	@Autowired
	private GameUtil gameUtil;

	@Override
	public GamePlayerDTO create(GamePlayerDTO gamePlayerDTO) throws Exception {
		GamePlayer player = gameUtil.convert(gamePlayerDTO, GamePlayer.class);
		return gameUtil.convert(gamePlayerRepository.save(player), GamePlayerDTO.class);
	}

	@Override
	public GamePlayerDTO update(GamePlayerDTO gamePlayerDTO) throws Exception {
		return gameUtil.convert(gamePlayerRepository.save(gameUtil.convert(gamePlayerDTO, GamePlayer.class)),
				GamePlayerDTO.class);
	}

	@Override
	public void delete(int id) throws Exception {
		gamePlayerRepository.deleteById(id);

	}

	@Override
	public GamePlayerDTO findById(int id) throws Exception {
		return gameUtil.convert(gamePlayerRepository.findById(id).orElse(null), GamePlayerDTO.class);
	}

	@Override
	public void deleteAll() throws Exception {
		gamePlayerRepository.deleteAll();

	}

	@Override
	public List<GamePlayerDTO> findByGameInstanceId(GameInstance gameInstanceId) throws Exception {
		List<GamePlayerDTO> result = new ArrayList<>();
		System.out.println("gam,e instanc id " + gameInstanceId);
		List<GamePlayer> players = gamePlayerRepository.findByGameInstanceId(gameInstanceId);
		GameInstanceDTO dto = gameUtil.convert(gameInstanceId, GameInstanceDTO.class);
		if (null != players) {
			result = players.stream()
					.map(g -> new GamePlayerDTO(g.getId(), g.isInitiator(), g.getCreatedOn(), g.getScore(),
							g.getSavedState(), g.getStatus(), dto, gameUtil.convert(g.getPlayer(), PlayerDTO.class),
							g.getIpAddress(), g.getPort()))
					.collect(Collectors.toList());
		}
		return result;
	}

	@Override
	public List<GamePlayerDTO> findAll() throws Exception {
		List<GamePlayerDTO> result = new ArrayList<>();
		List<GamePlayer> gamePlayers = gamePlayerRepository.findAll();
		if (null != gamePlayers) {
			result = gamePlayers.stream()
					.map(g -> new GamePlayerDTO(g.getId(), g.isInitiator(), g.getCreatedOn(), g.getScore(),
							g.getSavedState(), g.getStatus(),
							gameUtil.convert(g.getGameInstanceId(), GameInstanceDTO.class),
							gameUtil.convert(g.getPlayer(), PlayerDTO.class), g.getIpAddress(), g.getPort()))
					.collect(Collectors.toList());
		}

		return result;
	}

}
