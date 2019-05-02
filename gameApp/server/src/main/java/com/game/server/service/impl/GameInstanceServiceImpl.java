package com.game.server.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.server.entity.Game;
import com.game.server.entity.GameInstance;
import com.game.server.entity.GamePlayer;
import com.game.server.entity.dto.GameDTO;
import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.enums.GameStatus;
import com.game.server.repository.GameInstancerRepository;
import com.game.server.repository.GamePlayerRepository;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GameUtil;

import javassist.NotFoundException;

/**
 * The Class GameInstanceServiceImpl.
 */
@Service
@Transactional
public class GameInstanceServiceImpl implements GameInstanceService {

	@Autowired
	private GameInstancerRepository gameInstanceRepo;

	@Autowired
	private GamePlayerRepository gamePlayerRepository;

	@Autowired
	private GameUtil gameUtil;

	@Override
	public GameInstanceDTO create(GameInstanceDTO gameInstanceDTO) throws Exception {
		return gameUtil.convert(gameInstanceRepo.save(gameUtil.convert(gameInstanceDTO, GameInstance.class)),
				GameInstanceDTO.class);
	}

	@Override
	public GameInstanceDTO update(int id, GameInstanceDTO gameInstanceDTO) throws Exception {
		GameInstance temp = null;
		if (gameInstanceDTO.getId() == 0) {
			temp = gameInstanceRepo.findById(id).get();
			if (null == temp) {
				throw new NotFoundException("Game Instance not found for id " + id);
			}
			temp.setGame(gameUtil.convert(gameInstanceDTO.getGame(), Game.class));
			temp.setStatus(gameInstanceDTO.getStatus());
			temp.setGame(gameUtil.convert(gameInstanceDTO.getGame(), Game.class));
		} else {
			temp = gameUtil.convert(gameInstanceDTO, GameInstance.class);
		}
		gameInstanceRepo.save(temp);
		return gameInstanceDTO;
	}

	@Override
	public GameInstanceDTO findById(int id) throws Exception {
		return gameUtil.convert(gameInstanceRepo.findById(id).orElse(null), GameInstanceDTO.class);
	}

	@Override
	public List<GameInstanceDTO> findAll() throws Exception {
		return gameInstanceRepo.findAll().stream()
				.map(p -> new GameInstanceDTO(gameUtil.convert(p.getGame(), GameDTO.class), p.getStatus()))
				.collect(Collectors.toList());
	}

	@Override
	public void delete(int id) throws Exception {
		gameInstanceRepo.deleteById(id);
	}

	@Override
	public boolean updateStatus(int id, GameStatus status) throws Exception {
		GameInstance instance = gameInstanceRepo.findById(id).get();
		instance.setStatus(status);

		gameInstanceRepo.save(instance);
		return true;
	}

	/**
	 * Method to update the status of a game player
	 */
	public void updatePlayerStatus(int id, GameStatus status) throws Exception {
		GamePlayer player = gamePlayerRepository.findById(id).orElse(null);
		if (null != player) {
			player.setStatus(status);
			gamePlayerRepository.save(player);
		}

	}

	@Override
	public void deleteAll() throws Exception {
		gameInstanceRepo.deleteAll();
	}

}
