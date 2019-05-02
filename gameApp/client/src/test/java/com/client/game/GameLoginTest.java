package com.client.game;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.client.game.core.IGameLogic;
import com.client.game.dto.AuditDTO;
import com.client.game.dto.GameDTO;
import com.client.game.enums.GameStatus;
import com.client.game.number.NumberGameLogic;
import com.client.game.service.IGame;
import com.client.game.service.impl.Game;
import com.client.game.util.RestTemplateUtil;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClientApplication.class)
@WebAppConfiguration
public class GameLoginTest {

	@Autowired
	NumberGameLogic gameLogic;
	
	@Mock
	RestTemplateUtil restUtil;
	
	@Test
	public void calculateNumber1() throws Exception{
		AuditDTO input=new AuditDTO(0,0,0,"",GameStatus.NEW,1,2,1,1,"ipadress",0);
		gameLogic.setInputDTO(input);
		gameLogic.setStartNumber(40);
		input=gameLogic.process();
		assertEquals(-1, input.getCalculatedNumber());
		assertEquals(13, input.getResult());
	}
	
	@Test (expected=Exception.class)
	public void calculateNumber2() throws Exception{
		AuditDTO input=new AuditDTO(0,0,0,"",GameStatus.NEW,1,2,1,1,"ipadress",0);
		gameLogic.setInputDTO(input);
		gameLogic.setStartNumber(2);
		input=gameLogic.process();
	}
	
	@Test
	public void calculateNumber3() throws Exception{
		AuditDTO input=new AuditDTO(0,0,0,"",GameStatus.RUNNING,1,2,1,1,"ipadress",0);
		input.setNumber(14);
		input.setResult(14);
		input.setCalculatedNumber(1);
		gameLogic.setInputDTO(input);
		input=gameLogic.process();
		assertEquals(5, input.getResult());
	}
	
	@Test
	public void calculateNumber4() throws Exception{
		AuditDTO input=new AuditDTO(0,0,0,"",GameStatus.RUNNING,1,2,1,1,"ipadress",0);
		input.setCalculatedNumber(1);
		input.setNumber(2);
		input.setResult(2);
		gameLogic.setInputDTO(input);
		input=gameLogic.process();
		assertEquals(1, input.getCalculatedNumber());
		assertEquals(1, input.getResult());
		assertEquals(GameStatus.END, input.getGameStatus());
	}
}
