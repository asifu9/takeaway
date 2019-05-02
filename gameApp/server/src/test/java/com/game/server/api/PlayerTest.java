package com.game.server.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.game.server.ServerApplication;
import com.game.server.entity.Player;
import com.game.server.entity.dto.PlayerDTO;
import com.game.server.service.GameInstanceService;
import com.game.server.service.PlayerService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@WebAppConfiguration
//@IntegrationTest("server.port:0")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerTest {

	   @Autowired
	    private PlayerService playerService;
	   
	   @Autowired
	   private GameInstanceService gameInstanceService;

	
	  @Test
	    public void createTest() throws Exception
	    {
	        PlayerDTO player = new PlayerDTO();
	        player.setFirstName("Max");
	        player.setLastName("Well");
	        player.setUserName("mwell");
	        player=playerService.create(player);
	        PlayerDTO p = playerService.findById(player.getId());
	        assertNotNull(p);
	        gameInstanceService.deleteAll();
	        playerService.deleteAll();
	        PlayerDTO del = playerService.findById(player.getId());
	        assertNull(del);
	    }
	  
	  @Test
	    public void updateTest() throws Exception
	    {
		  PlayerDTO player = new PlayerDTO();
	        player.setFirstName("Max");
	        player.setLastName("Well");
	        player.setUserName("mwell");
	        player=playerService.create(player);
	        PlayerDTO p = playerService.findById(player.getId());
	       
	        p.setLastName("Life");
	        playerService.update(p);
	        
	        
	        PlayerDTO pla = playerService.findById(p.getId());
	        assertEquals("Max", pla.getFirstName());
	        assertEquals("Life", pla.getLastName());
	        gameInstanceService.deleteAll();
	        playerService.deleteAll();
	    }

}
