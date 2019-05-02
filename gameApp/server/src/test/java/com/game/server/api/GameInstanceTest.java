package com.game.server.api;

import static org.junit.Assert.assertEquals;
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
import com.game.server.entity.dto.GameDTO;
import com.game.server.entity.dto.GameInstanceDTO;
import com.game.server.entity.dto.PlayerDTO;
import com.game.server.enums.GameStatus;
import com.game.server.enums.GameType;
import com.game.server.service.GameInstanceService;
import com.game.server.service.GameService;
import com.game.server.service.GameUtil;
import com.game.server.service.PlayerService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WebAppConfiguration

//@IntegrationTest("server.port:0")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameInstanceTest {

	   @Autowired
	    private GameInstanceService gameInstanceService;
	   @Autowired
	    private GameService gameService;
	   @Autowired
	   private PlayerService playerService;
	   
	   @Autowired
	   private GameUtil gameUtil;
	   
	   PlayerDTO player=null;
	   GameDTO game=null;
	   
	   @Before
	   public void setUp() throws Exception{
//		   gameInstanceService.deleteAll();
//		   gameService.deleteAll();
//		   
//		   playerService.deleteAll();
	
			player=playerService.create(new PlayerDTO("Tom", "Harris",Calendar.getInstance().getTimeInMillis(), 
					"tharris","img","ip",2));
		  	game=gameService.create(new GameDTO("CounterStrike",GameType.NUMBER));
	   }
	
	  @Test
	    public void testCreate() throws Exception
	    {
		  	
		  
	        GameInstanceDTO gameInstance = new GameInstanceDTO();
		    gameInstance.setGame(game);
		    gameInstance.setStatus(GameStatus.RUNNING);

		    
	        gameInstance=gameInstanceService.create(gameInstance);
	        GameInstanceDTO p = gameInstanceService.findById(gameInstance.getId());
	        assertNotNull(p);
	        gameInstanceService.deleteAll();
	        GameInstanceDTO del = gameInstanceService.findById(gameInstance.getId());
	        assertNull(del);
	    }
	  
	  @Test
	    public void updateTest() throws Exception
	    {
		  
	        GameInstanceDTO gameInstance = new GameInstanceDTO();
		    gameInstance.setGame(game);
		    gameInstance.setStatus(GameStatus.RUNNING);
	        gameInstance=gameInstanceService.create(gameInstance);
	        
	        GameInstanceDTO intance = gameInstanceService.findById(gameInstance.getId());
		  	GameDTO gameNew=gameService.create(new GameDTO("Number",GameType.NUMBER));
	        intance.setGame(gameNew);	
	        intance.setStatus(GameStatus.END);
	        gameInstanceService.update(intance.getId(), intance);
	        
	        GameInstanceDTO fetchedData = gameInstanceService.findById(gameInstance.getId());
	        assertEquals(gameNew.getId(), fetchedData.getGame().getId());
	        assertEquals(GameStatus.END, fetchedData.getStatus());
	        		
	        gameInstanceService.deleteAll();
	    }
	  
	  @Test
	    public void fetchTest() throws Exception
	    {
		  	gameInstanceService.deleteAll();
	        
		  	GameInstanceDTO gameInstance = new GameInstanceDTO();
		    gameInstance.setGame(game);
		    gameInstance.setStatus(GameStatus.RUNNING);
	        gameInstance=gameInstanceService.create(gameInstance);
	        
	     	GameInstanceDTO gameInstance1 = new GameInstanceDTO();
		    gameInstance1.setGame(game);
		    gameInstance1.setStatus(GameStatus.RUNNING);
	        gameInstance1=gameInstanceService.create(gameInstance1);
	        
	        List<GameInstanceDTO> list = gameInstanceService.findAll();
	        assertEquals(2, list.size());
	        		
	        gameInstanceService.deleteAll();
	    }

}
