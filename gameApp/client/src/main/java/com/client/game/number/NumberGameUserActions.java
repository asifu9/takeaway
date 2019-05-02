package com.client.game.number;

import org.springframework.beans.factory.annotation.Autowired;

import com.client.game.core.IUserAction;
import com.client.game.dto.AuditDTO;
import com.client.game.enums.UserActions;


public class NumberGameUserActions implements IUserAction {

	@Autowired
	NumberGame game;
	
	@Override
	public void move(UserActions actions) throws Exception{
		throw new Exception("For this game, this action is not applible");
	}

	@Override
	public void action(Object object) throws Exception{
		game.play((AuditDTO)object);
		
	}

}
