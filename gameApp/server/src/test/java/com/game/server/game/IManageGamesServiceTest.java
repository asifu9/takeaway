package com.game.server.game;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.game.server.ServerApplication;
import com.game.server.entity.dto.GameDTO;
import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.entity.dto.PlayerDTO;
import com.game.server.enums.GameStatus;
import com.game.server.enums.GameType;
import com.game.server.game.number.NumberGameUserActionImpl;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GamePlayerService;
import com.game.server.service.GameService;
import com.game.server.service.PlayerService;
import com.game.server.util.RestTemplateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WebAppConfiguration
// @RunWith(MockitoJUnitRunner.class)
public class IManageGamesServiceTest {

	@Autowired
	private NumberGameUserActionImpl service;

	@Mock
	RestTemplateUtil restUtil;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private GamePlayerService gamePlayerService;

	@Autowired
	private GameInstanceService gameInstanceService;
	@Autowired
	GameService gameService;
	GameInstanceDTO gameInstanceDTO = null;
	GameDTO gameDTO = null;
	PlayerDTO playerDTO1 = null;
	PlayerDTO playerDTO2 = null;

	@Before
	public void setUp() throws Exception {
		playerService.deleteAll();
		gameInstanceService.deleteAll();
		gameService.deleteAll();
		GameDTO game = new GameDTO("Number", GameType.NUMBER);
		playerDTO1 = playerService.create(
				new PlayerDTO("Tom", "Harris", Calendar.getInstance().getTimeInMillis(), "tharris", "img", "ip", 0));
		playerDTO2 = playerService
				.create(new PlayerDTO("Max", "Well", Calendar.getInstance().getTimeInMillis(), "maxi", "img", "ip", 0));

		gameDTO = gameService.create(game);

	}

	@Test
	public void testGameStarted() throws Exception {
		RestTemplateUtil person1 = (RestTemplateUtil) Mockito.spy(restUtil);
		service.setRestTemplateUtil(person1);
		Mockito.doReturn(null).when(person1).postData("http://null:0/client/number/endpoint/first/", null, null);
		// when(restUtil.postData("http://null:0/client/number/endpoint/first/",
		// null, Object.class)).thenReturn("done");
		GameInstanceDTO gameInstance = new GameInstanceDTO();
		gameInstance.setGame(gameDTO);
		gameInstance.setStatus(GameStatus.RUNNING);
		service.startGame(gameInstance, playerDTO1.getId());
		service.startGame(gameInstance, playerDTO2.getId());

		List<GameInstanceDTO> list = gameInstanceService.findAll();
		assertEquals(1, list.size());
		gamePlayerService.deleteAll();
		gameInstanceService.deleteAll();

	}

	// org.springframework.web.client.ResourceAccessException: I/O error on POST
	// request for "http://null:0/client/number/endpoint/first/": null; nested
	// exception is java.net.UnknownHostException: null

}
