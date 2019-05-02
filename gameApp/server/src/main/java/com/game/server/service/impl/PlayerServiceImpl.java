package com.game.server.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.server.entity.Player;
import com.game.server.entity.dto.PlayerDTO;
import com.game.server.repository.PlayerRepository;
import com.game.server.service.GameUtil;
import com.game.server.service.PlayerService;

/**
 * The Class PlayerServiceImpl.
 */
@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameUtil gameUtil;

	@Override
	public PlayerDTO create(PlayerDTO playerDTO) throws Exception {
		Player player = playerRepository.save(gameUtil.convert(playerDTO, Player.class));
		return gameUtil.convert(player, PlayerDTO.class);
	}

	@Override
	public PlayerDTO update(PlayerDTO playerDTO) throws Exception {
		Player player = playerRepository.save(gameUtil.convert(playerDTO, Player.class));
		return gameUtil.convert(player, PlayerDTO.class);
	}

	@Override
	public List<PlayerDTO> fetchAll() throws Exception {
		List<Player> players = playerRepository.findAll();

		return players.stream()
				.map(p -> new PlayerDTO(p.getId(), p.getFirstName(), p.getLastName(), p.getDateOfBirth(),
						p.getUserName(), p.getProfileImagePath(), p.getIpAddress(), p.getPort()))
				.collect(Collectors.toList());

	}

	@Override
	public void delete(int id) throws Exception {
		playerRepository.deleteById(id);
	}

	@Override
	public PlayerDTO findById(int id) throws Exception {
		Player player = playerRepository.findById(id).orElse(null);
		return gameUtil.convert(player, PlayerDTO.class);
	}

	@Override
	public void deleteAll() throws Exception {
		playerRepository.deleteAll();

	}

	@Override
	public PlayerDTO findByUserName(String username) {
		return gameUtil.convert(playerRepository.findByUserName(username), PlayerDTO.class);

	}

}
