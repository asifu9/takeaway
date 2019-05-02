package com.client.game.enums;

public enum GameStatus {
	NEW,
	
	/** The running. */
	RUNNING,
	
	/** The canceled. */
	CANCELED,
	
	/** The paused. */
	PAUSED,
	
	/** The end. */
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
