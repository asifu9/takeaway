package com.client.game.dto;

import com.client.game.enums.GameStatus;

/**
 * DTO class for Game instance entity
 *
 */
public class GameInstanceDTO {

	private int id;

	private GameDTO game;

	private GameStatus status;

	/**
	 * Instantiates a new game instance.
	 */
	public GameInstanceDTO() {
	}

	public GameInstanceDTO(GameDTO game, GameStatus status) {
		this.game = game;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public GameDTO getGame() {
		return game;
	}

	public void setGame(GameDTO game) {
		this.game = game;
	}

	private String ipAddress;
	private int port;

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
