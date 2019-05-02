package com.game.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.server.entity.Game;
import com.game.server.entity.dto.GameDTO;
import com.game.server.enums.GameType;
import com.game.server.repository.GameRepository;
import com.game.server.service.GameService;
import com.game.server.service.GameUtil;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private GameUtil gameUtil;

	@Override
	public GameDTO create(GameDTO gameDTO) throws Exception {
		return gameUtil.convert(gameRepository.save(gameUtil.convert(gameDTO, Game.class)), GameDTO.class);
	}

	@Override
	public List<GameDTO> findByGameType(GameType gameType) {
		List<Game> games = gameRepository.findByGameType(gameType);
		List<GameDTO> dtoList = new ArrayList<>();
		if (null != games) {
			dtoList = games.stream().map(p -> new GameDTO(p.getId(), p.getName(), p.getGameType()))
					.collect(Collectors.toList());
		}
		return dtoList;
	}

	@Override
	public List<GameDTO> findAll() {
		List<Game> games = gameRepository.findAll();
		List<GameDTO> dtoList = new ArrayList<>();
		if (null != games) {
			dtoList = games.stream().map(p -> new GameDTO(p.getId(), p.getName(), p.getGameType()))
					.collect(Collectors.toList());
		}
		return dtoList;
	}

	@Override
	public void delete(int id) throws Exception {
		gameRepository.deleteById(id);
	}

	@Override
	public GameDTO findById(int gameId) throws Exception {
		return gameUtil.convert(gameRepository.findById(gameId).orElse(null), GameDTO.class);
	}

	@Override
	public GameDTO update(int gameId, GameDTO game) throws Exception {
		Game updatedGame = null;
		if (game.getId() == 0) {
			// fetch game object
			updatedGame = gameRepository.findById(gameId).get();
			updatedGame.setName(game.getName());
			updatedGame.setGameType(game.getGameType());
		} else {
			updatedGame = gameUtil.convert(game, Game.class);
		}
		updatedGame = gameRepository.save(updatedGame);
		return gameUtil.convert(updatedGame, GameDTO.class);
	}

	@Override
	public void deleteAll() throws Exception {
		gameRepository.deleteAll();

	}

}
