package com.game.server.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.game.server.entity.dto.GameDTO;
import com.game.server.enums.GameType;
import com.game.server.service.GameService;

@Component
@Order(value=2)
public class GameSetup implements CommandLineRunner{


	/** The game service. */
	@Autowired
	private GameService gameService;
	
	
	@Value("${game.populate.default.data}")
	public  boolean INITIALIZE_DEFAULT_DATA;
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	public void run(String... args) throws Exception {
		if(!INITIALIZE_DEFAULT_DATA){
			return;
		}
		GameDTO game=new GameDTO();
		game.setGameType(GameType.NUMBER);
		game.setName("NumberGame");
		gameService.create(game);
		
		
		
	
	}
}
