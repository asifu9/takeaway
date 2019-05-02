package com.game.server.enums;

/**
 * Enum class to represent a game status
 *
 */
public enum GameStatus {
	NEW,
	
	RUNNING,
	
	CANCELED,
	
	PAUSED,
	
	END;
	
	public static GameStatus getValue(String type){
		GameStatus status=null;
		switch (type){
			case "NEW":
				status= GameStatus.NEW;
			case "RUNNING":
				status= GameStatus.RUNNING;
			case "CANCELED":
				status= GameStatus.CANCELED;
			case "PAUSED":
				status= GameStatus.PAUSED;
			case "END":
				status= GameStatus.END;
				
		}
		return status;
	}
}
