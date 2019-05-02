package com.game.server.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.game.server.ServerApplication;
import com.game.server.entity.dto.GamePlayerDTO;
import com.game.server.entity.dto.PlayerDTO;
import com.game.server.enums.GameStatus;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GamePlayerService;
import com.game.server.service.GameService;
import com.game.server.service.PlayerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WebAppConfiguration

// @IntegrationTest("server.port:0")
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GamePlayerTest {

	@Autowired
	private GamePlayerService gamePlayerService;
	   @Autowired
	    private GameService gameService;
	   @Autowired
	   private PlayerService playerService;
	   @Autowired
	   private GameInstanceService gameInstanceService;
	   
	   PlayerDTO player=null;
	   //Game game=null;
	   
	   @Before
	   public void setUp() throws Exception{
			player=playerService.create(new PlayerDTO("Tom", "Harris",Calendar.getInstance().getTimeInMillis(),
					"tharris","imgae","ip",0));
	   }
	   
	@Test
	public void createTest() throws Exception {

		GamePlayerDTO gamePlayer = new GamePlayerDTO(true,Calendar.getInstance().getTimeInMillis(),0,0,GameStatus.NEW,null,null,"",0);

		gamePlayer = gamePlayerService.create(gamePlayer);
		GamePlayerDTO p = gamePlayerService.findById(gamePlayer.getId());
		assertNotNull(p);
		gameInstanceService.deleteAll();
		gamePlayerService.deleteAll();
		GamePlayerDTO del = gamePlayerService.findById(gamePlayer.getId());
		assertNull(del);
	}

	@Test
	public void updateTest() throws Exception {

		GamePlayerDTO gamePlayer = new GamePlayerDTO(true,Calendar.getInstance().getTimeInMillis(),0,0,GameStatus.NEW,null,null,"",0);

		gamePlayer = gamePlayerService.create(gamePlayer);

		GamePlayerDTO p = gamePlayerService.findById(gamePlayer.getId());
		p.setInitiator(false);
		gamePlayerService.update(p);

		GamePlayerDTO updatedGame = gamePlayerService.findById(p.getId());
		assertFalse(updatedGame.isInitiator());
		gameInstanceService.deleteAll();
		gamePlayerService.deleteAll();

	}

	@Test
	public void fetchTest() throws Exception {
		gameInstanceService.deleteAll();
		gamePlayerService.deleteAll();
		
		GamePlayerDTO gamePlayer = new GamePlayerDTO(true,Calendar.getInstance().getTimeInMillis(),0,0,GameStatus.NEW,null,null,"",0);

		gamePlayer = gamePlayerService.create(gamePlayer);
		
		GamePlayerDTO gamePlayer2 = new GamePlayerDTO(false,Calendar.getInstance().getTimeInMillis(),0,0,GameStatus.NEW,null,null,"",0);

		gamePlayer2 = gamePlayerService.create(gamePlayer2);

		
		assertNotNull(gamePlayer2);
		List<GamePlayerDTO>  list=gamePlayerService.findAll();
		assertEquals(2, list.size());
		gameInstanceService.deleteAll();
		gamePlayerService.deleteAll();

	}

}
