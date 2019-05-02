package com.game.server.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.game.server.ServerApplication;
import com.game.server.entity.Game;
import com.game.server.entity.dto.GameDTO;
import com.game.server.enums.GameType;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GameService;
//
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)

//@WebAppConfiguration
// @IntegrationTest("server.port:0")
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTest {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private GameInstanceService gameInstanceService;

	@Test
	public void createTest() throws Exception {
		GameDTO game = new GameDTO();
		game.setGameType(GameType.NUMBER);
		game.setName("Number Exchange");

		game = gameService.create(game);
		GameDTO p = gameService.findById(game.getId());
		assertNotNull(p);
		gameInstanceService.deleteAll();
		gameService.deleteAll();
		GameDTO del = gameService.findById(game.getId());
		assertNull(del);
	}

	@Test
	public void updateTest() throws Exception {
		GameDTO game = new GameDTO();
		game.setGameType(GameType.NUMBER);
		game.setName("Number Game");
		game = gameService.create(game);
		GameDTO p = gameService.findById(game.getId());
		assertNotNull(p);

		p.setName("IPL");
		p.setGameType(GameType.CRICKET);

		gameService.update(p.getId(), p);
		GameDTO updateGame = gameService.findById(game.getId());
		assertEquals("IPL", updateGame.getName());
		assertEquals(GameType.CRICKET, updateGame.getGameType());
		gameInstanceService.deleteAll();
		gameService.deleteAll();

	}

	@Test
	public void fetchTest() throws Exception {
		gameInstanceService.deleteAll();
		gameService.deleteAll();
		GameDTO game = new GameDTO();
		game.setGameType(GameType.NUMBER);
		game.setName("Number");
		game = gameService.create(game);

		GameDTO game1 = new GameDTO();
		game1.setGameType(GameType.CRICKET);
		game1.setName("IPL");
		game1 = gameService.create(game1);

		List<GameDTO> list = gameService.findAll();
		assertEquals(2, list.size());

		List<GameDTO> list2 = gameService.findByGameType(GameType.CRICKET);
		assertEquals(1, list2.size());
		gameInstanceService.deleteAll();
		gameService.deleteAll();

	}

}
