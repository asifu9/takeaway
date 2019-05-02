package com.client.game.core;

import com.client.game.enums.UserActions;

/**
 * Interface to hold user action methods
 * 
 */
public interface IUserAction {

	/**
	 * Move action of each user on the game
	 * 
	 * @param actions
	 * @throws Exception
	 */
	void move(UserActions actions) throws Exception;

	/**
	 * Action performed using keyboard/mouse
	 * 
	 * @param object
	 * @throws Exception
	 */
	void action(Object object) throws Exception;
}
