package com.game.server.setup;

import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.game.server.entity.dto.PlayerDTO;
import com.game.server.service.PlayerService;

@Component
@Order(value=2)
public class PlayerSetup implements CommandLineRunner{

	/** The player service. */
	@Autowired
	private PlayerService playerService;
	
	@Value("${game.populate.default.data}")
	public  boolean INITIALIZE_DEFAULT_DATA;

	@Override
	public void run(String... args) throws Exception {
		if(!INITIALIZE_DEFAULT_DATA){
			return;
		}
		PlayerDTO p=new PlayerDTO();
		p.setFirstName("Max");
		p.setLastName("Well");
		p.setUserName("mwell");
		p.setIpAddress("localhost");
		p.setPort(2020);
		p.setDateOfBirth(Calendar.getInstance().getTimeInMillis());
		p.setProfileImagePath("/images/user1.png");
		playerService.create(p);
		
		PlayerDTO p2=new PlayerDTO();
		p2.setFirstName("Brain");
		p2.setLastName("Lara");
		p2.setUserName("bLara");
		p2.setIpAddress("localhost");
		p2.setPort(3030);
		p2.setProfileImagePath("/images/user2.png");
		p2.setDateOfBirth(Calendar.getInstance().getTimeInMillis());
		playerService.create(p2);
		
	}
}
