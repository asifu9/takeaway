package com.client.game.dto;

import com.client.game.enums.GameStatus;

/**
 * DTO class for Game Player entity
 *
 */
public class GamePlayerDTO {

	private int id;

	private boolean isInitiator;

	private long createdOn;

	private float score;

	private int savedState;

	private GameStatus status;

	private GameInstanceDTO gameInstanceId;

	private PlayerDTO player;

	private String ipAddress;

	private int port;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GamePlayerDTO() {

	}

	public GamePlayerDTO(boolean isInitiator, long createdOn, float score, int savedState, GameStatus status,
			GameInstanceDTO gameDTO, PlayerDTO player, String ipAddress, int port) {
		this.isInitiator = isInitiator;
		this.createdOn = createdOn;
		this.score = score;
		this.savedState = savedState;
		this.status = status;
		this.gameInstanceId = gameDTO;
		this.player = player;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public GamePlayerDTO(int id, boolean isInitiator, long createdOn, float score, int savedState, GameStatus status,
			GameInstanceDTO gameDTO, PlayerDTO player, String ipAddress, int port) {
		this.id = id;
		this.isInitiator = isInitiator;
		this.createdOn = createdOn;
		this.score = score;
		this.savedState = savedState;
		this.status = status;
		this.gameInstanceId = gameDTO;
		this.player = player;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public boolean isInitiator() {
		return isInitiator;
	}

	public void setInitiator(boolean isInitiator) {
		this.isInitiator = isInitiator;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getSavedState() {
		return savedState;
	}

	public void setSavedState(int savedState) {
		this.savedState = savedState;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public GameInstanceDTO getGameInstanceId() {
		return gameInstanceId;
	}

	public void setGameInstanceId(GameInstanceDTO gameInstanceId) {
		this.gameInstanceId = gameInstanceId;
	}

	public PlayerDTO getPlayer() {
		return player;
	}

	public void setPlayer(PlayerDTO player) {
		this.player = player;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
